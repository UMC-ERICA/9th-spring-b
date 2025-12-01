package umc.server.domain.user.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;

public class UserResDTO {
    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createAt
    ){}

    @Builder
    public record LoginDTO(
            Long memberId,
            String accessToken
    ){}
}
