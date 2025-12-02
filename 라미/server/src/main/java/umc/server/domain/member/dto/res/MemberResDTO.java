package umc.server.domain.member.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Setter;

import java.time.LocalDateTime;

public class MemberResDTO {

    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createAt
    ) {}

    @Builder
    public record LoginDTO(
            Long memberId,
            String accessToken
    ){}

    // 카카오 토큰 응답 DTO
    @Builder
    public record KakaoTokenResponse(
            @JsonProperty("access_token")
            String accessToken,

            @JsonProperty("token_type")
            String tokenType,

            @JsonProperty("refresh_token")
            String refreshToken,

            @JsonProperty("expires_in")
            Integer expiresIn,

            @JsonProperty("refresh_token_expires_in")
            Integer refreshTokenExpiresIn
    ) {}

    // 카카오 사용자 정보 응답 DTO
    public record KakaoUserInfoResponse(
            Long id,  // 카카오 회원번호

            @JsonProperty("kakao_account")
            KakaoAccount kakaoAccount
    ) {
        public record KakaoAccount(
                String email,
                Profile profile
        ) {
            public record Profile(
                    String nickname
            ) {}
        }
    }
}
