package umc.server.domain.member.dto.res;

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
}
