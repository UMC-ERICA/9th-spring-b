package umc.server.domain.mission.dto.res;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

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
    ) {}

    @Builder
    public record MissionPreViewListDTO (
            List<MissionPreViewDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record MissionPreViewDTO(
            String missionTitle,
            String missionDescription,
            LocalDate deadline,
            Integer rewardPoints
    ) {}
}
