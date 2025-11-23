package umc.server.domain.mission.dto.res;

import lombok.Builder;
import umc.server.domain.mission.enums.MissionStatus;

public class MemberMissionResDTO {

    @Builder
    public record ChallengeResponse(
            MissionStatus missionStatus,
            Long memberMissionId,
            Long missionId,
            Long memberId
    ) {
    }
}
