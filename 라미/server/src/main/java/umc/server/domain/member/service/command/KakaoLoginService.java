package umc.server.domain.member.service.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.converter.MemberConverter;
import umc.server.domain.member.dto.res.MemberResDTO;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.entity.RefreshToken;
import umc.server.domain.member.enums.Gender;
import umc.server.domain.member.enums.SocialProvider;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.member.repository.RefreshTokenRepository;
import umc.server.domain.member.service.kakao.KakaoApiService;
import umc.server.global.auth.enums.Role;
import umc.server.global.auth.jwt.JwtUtil;
import umc.server.global.auth.service.CustomUserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KakaoLoginService {

    private final KakaoApiService kakaoApiService;
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public MemberResDTO.LoginDTO kakaoLogin(String code) {
        // 인가 코드로 액세스 토큰 받기
        MemberResDTO.KakaoTokenResponse tokenResponse = kakaoApiService.getAccessToken(code);

        // 액세스 토큰으로 사용자 정보 받기
        MemberResDTO.KakaoUserInfoResponse userInfo = kakaoApiService.getUserInfo(tokenResponse.accessToken());

        // 카카오 ID로 기존 회원 찾기
        Member member = memberRepository
                .findBySocialProviderAndSocialId(SocialProvider.KAKAO, String.valueOf(userInfo.id()))
                .orElseGet(() -> createKakaoMember(userInfo));

        // JWT 토큰 발급
        CustomUserDetails userDetails = new CustomUserDetails(member);
        String accessToken = jwtUtil.createAccessToken(userDetails);
        String refreshToken = jwtUtil.createRefreshToken(userDetails);

        // RefreshToken 만료 시간 추출
        LocalDateTime expiryDate = jwtUtil.getExpiryDate(refreshToken)
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        // 기존 RefreshToken 조회
        RefreshToken refreshTokenEntity = refreshTokenRepository.findByMember(member)
                .orElse(null);

        if (refreshTokenEntity != null) {
            // 기존 토큰이 있으면 업데이트
            refreshTokenEntity.updateToken(refreshToken, expiryDate);
        } else {
            // 없으면 새로 생성
            refreshTokenEntity = RefreshToken.builder()
                    .member(member)
                    .token(refreshToken)
                    .expiryDate(expiryDate)
                    .build();
            refreshTokenRepository.save(refreshTokenEntity);
        }
        return MemberConverter.toLoginDTO(member, accessToken, refreshToken);
    }

    // 카카오 회원 자동 가입
    private Member createKakaoMember(MemberResDTO.KakaoUserInfoResponse userInfo) {
        Member newMember = Member.builder()
                .name(userInfo.kakaoAccount().profile().nickname())
                .email(userInfo.kakaoAccount().email())
                .gender(Gender.NONE)  // 카카오는 성별 정보 제공 안 함
                .birth(LocalDate.now())  // 카카오는 생년월일 정보 제공 안 함 (또는 기본값 설정)
                .address("")  // 카카오는 주소 정보 제공 안 함
                .password(null)  // 소셜 로그인은 비밀번호 없음
                .role(Role.ROLE_USER)
                .socialProvider(SocialProvider.KAKAO)
                .socialId(String.valueOf(userInfo.id()))
                .build();

        return memberRepository.save(newMember);
    }
}