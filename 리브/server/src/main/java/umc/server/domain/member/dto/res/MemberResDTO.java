package umc.server.domain.member.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDTO {

    @Builder
    public record JoinResponse(
            Long memberId,
            LocalDateTime createdAt
    ) {}

    // 로그인
    @Builder
    public record LoginResponse(
            Long memberId,
            String accessToken
    ) {}
}
