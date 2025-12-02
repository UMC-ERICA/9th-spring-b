package umc.server.global.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import umc.server.global.auth.service.CustomUserDetails;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import java.time.Instant;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final SecretKey secretKey; // JWT 서명에 사용할 암호화 키
    private final Duration accessExpiration;

    public JwtUtil(
            @Value("${jwt.token.secretKey}") String secret, // application.yml 설정값 자동 주입
            @Value("${jwt.token.expiration.access}") Long accessExpiration
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessExpiration = Duration.ofMillis(accessExpiration); // 14400000ms → 4시간
    }

    // AccessToken 생성
    public String createAccessToken(CustomUserDetails user) {
        return createToken(user, accessExpiration);
    }

    // 토큰에서 이메일 추출
    public String getEmail(String token) {
        try {
            return getClaims(token).getPayload().getSubject(); // Parsing해서 Subject(이메일) 가져오기
        } catch (JwtException e) {
            return null;
        }
    }

    //  토큰 유효성 검증
    public boolean isValid(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    // 토큰 생성
    private String createToken(CustomUserDetails user, Duration expiration) {
        Instant now = Instant.now(); // 현재 시간

        // 권한 정보를 문자열로 변환
        String authorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .subject(user.getUsername()) // 주체 (이메일)
                .claim("role", authorities)
                .claim("email", user.getUsername())
                .issuedAt(Date.from(now)) // 발급 시간
                .expiration(Date.from(now.plus(expiration))) // 만료 시간
                .signWith(secretKey) // 비밀키로 서명
                .compact(); // JWT 문자열로 변환
    }

    // 토큰 정보 가져오기 - 토큰 파싱 및 검증
    private Jws<Claims> getClaims(String token) throws JwtException { // Claims: Payload
        return Jwts.parser() // JWT 파서 생성
                .verifyWith(secretKey)
                .clockSkewSeconds(60) // 서버 시간 오차 60초 허용
                .build()
                .parseSignedClaims(token); // 토큰 파싱 및 검증 - 실패 시 JwtException
    }
}
