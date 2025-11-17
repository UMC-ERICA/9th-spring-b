package umc.server.domain.mission.dto.res;

import lombok.Builder;

import java.time.LocalDate;

public class MissionResDTO {

    @Builder
    public record CreateMissionResponse(
            String missionTitle,
            String missionDescription,
            LocalDate deadline,
            int rewardPoints,
            boolean isActive,
            Long missionId,
            Long storeId
    ) {
    }
}
