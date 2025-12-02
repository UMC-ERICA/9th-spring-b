package umc.server.domain.member.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.converter.MemberConverter;
import umc.server.domain.member.dto.req.MemberReqDTO;
import umc.server.domain.member.dto.res.MemberResDTO;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.entity.RefreshToken;
import umc.server.domain.member.entity.mapping.MemberFood;
import umc.server.domain.member.exception.FoodException;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.exception.code.FoodErrorCode;
import umc.server.domain.member.exception.code.MemberErrorCode;
import umc.server.domain.member.repository.FoodRepository;
import umc.server.domain.member.repository.MemberFoodRepository;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.member.repository.RefreshTokenRepository;
import umc.server.global.auth.enums.Role;
import umc.server.global.auth.jwt.JwtUtil;
import umc.server.global.auth.service.CustomUserDetails;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;


    // 회원가입
    @Override
    public MemberResDTO.JoinDTO signup(MemberReqDTO.JoinDTO dto) {
        // 솔트된 비밀번호 생성
        String salt = passwordEncoder.encode(dto.getPassword());

        // 사용자 생성: 유저/관리자는 따로 API 만들어서 관리
        Member member = MemberConverter.toMember(dto, salt, Role.ROLE_USER);
        memberRepository.save(member);

        // 선호 음식 존재 여부 확인
        if (dto.getFoodCategory() != null && !dto.getFoodCategory().isEmpty()) {
            List<MemberFood> memberFoodList = dto.getFoodCategory().stream() // 스트림으로 dto의 음식 카테고리 순회
                    .map(category -> MemberFood.builder()
                            .member(member)
                            .food(foodRepository.findByCategory(category)
                                    .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND)))
                            .build()
                    )
                    .collect(Collectors.toList());
            memberFoodRepository.saveAll(memberFoodList);
        }
        /*if (dto.getFoodCategory() != null && !dto.getFoodCategory().isEmpty()) { // 체크 순서 중요(null에 empty 체크하면 에러)
            List<MemberFood> memberFoodList = new ArrayList<>();

            // 선호 음식 카테고리별 조회
            for (FoodCategory category : dto.getFoodCategory()) {
                Food food = foodRepository.findByCategory(category)
                        .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND)); // orElseThrow은 Optional 클래스 메서드

                // MemberFood 엔티티 생성
                MemberFood memberFood = MemberFood.builder()
                        .member(member)
                        .food(food)
                        .build();

                memberFoodList.add(memberFood);
            }
            memberFoodRepository.saveAll(memberFoodList);
        }*/

        // 응답 dto 생성
        return MemberConverter.toJoinDTO(member);
    }

    @Override
    public MemberResDTO.LoginDTO login(MemberReqDTO.LoginDTO dto) {
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 비번 검증
        if(!encoder.matches(dto.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.INVALID);
        }

        // jwt 토큰 발급용 UserDetails
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

    @Override
    public MemberResDTO.RefreshTokenDTO refresh(MemberReqDTO.RefreshTokenDTO dto) {
        String requestRefreshToken = dto.refreshToken();

        // RefreshToken 유효성 검증 (JWT 자체 검증)
        if (!jwtUtil.isValid(requestRefreshToken)) {
            throw new MemberException(MemberErrorCode.INVALID_REFRESH_TOKEN);
        }

        // DB에서 RefreshToken 조회
        RefreshToken refreshTokenEntity = refreshTokenRepository.findByToken(requestRefreshToken)
                .orElseThrow(() -> new MemberException(MemberErrorCode.REFRESH_TOKEN_NOT_FOUND));

        // 만료 여부 확인
        if (refreshTokenEntity.isExpired()) {
            // 만료된 토큰은 DB에서 삭제
            refreshTokenRepository.delete(refreshTokenEntity);
            throw new MemberException(MemberErrorCode.REFRESH_TOKEN_EXPIRED);
        }

        // 새로운 AccessToken 생성
        Member member = refreshTokenEntity.getMember();
        CustomUserDetails userDetails = new CustomUserDetails(member);
        String newAccessToken = jwtUtil.createAccessToken(userDetails);

        return MemberConverter.toRefreshTokenDTO(newAccessToken, requestRefreshToken);
    }
}
