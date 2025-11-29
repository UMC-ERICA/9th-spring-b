package umc.server.domain.mission.dto.req;

import jakarta.validation.constraints.NotNull;

public class MissionChallengeReqDto {

    public record ChallengeMissionDTO(
            @NotNull Long userId
    ) {}
}
