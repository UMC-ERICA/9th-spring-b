package umc.server.domain.member.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDTO {

    @Builder
    public record JoinResponse(
            Long memberId,
            LocalDateTime createdAt
    ) {}
}
