package umc.server.domain.mission.dto.req;

import java.time.LocalDate;

public class MissionReqDTO {

    public record CreateMissionRequest(
            String missionTitle,
            String missionDescription,
            LocalDate deadline,
            int rewardPoints,
            boolean isActive
    ) {
    }
}
