package umc.server.domain.mission.converter;

import umc.server.domain.mission.dto.req.MissionReqDTO;
import umc.server.domain.mission.dto.res.MissionResDTO;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.store.entity.Store;

public class MissionConverter {

    // entity -> DTO
    public static MissionResDTO.CreateMissionResponse toCreateMissionResponseDTO(Mission mission) {
        return MissionResDTO.CreateMissionResponse.builder()
                .missionTitle(mission.getMissionTitle())
                .missionDescription(mission.getMissionDescription())
                .deadline(mission.getDeadline())
                .rewardPoints(mission.getRewardPoints())
                .isActive(mission.isActive())
                .missionId(mission.getId())
                .storeId(mission.getId())
                .build();
    }

    // DTO -> entity
    public static Mission toMission(Store store, MissionReqDTO.CreateMissionRequest dto) {
        return Mission.builder()
                .missionTitle(dto.missionTitle())
                .missionDescription(dto.missionDescription())
                .deadline(dto.deadline())
                .rewardPoints(dto.rewardPoints())
                .isActive(dto.isActive())
                .build();
    }
}
