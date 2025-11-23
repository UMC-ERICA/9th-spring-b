package umc.server.domain.mission.dto.req;

public class MissionChallengeReqDTO {

    public record JoinDTO(
            Long userId,
            String status
    ) {}
}
