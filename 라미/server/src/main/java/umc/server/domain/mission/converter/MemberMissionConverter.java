package umc.server.domain.mission.converter;

import umc.server.domain.member.entity.mapping.MemberMission;
import umc.server.domain.mission.dto.res.MissionResDTO;

public class MemberMissionConverter {

    public static MissionResDTO.CompletedMissionDTO toCompletedMissionDTO(MemberMission memberMission) {
        return MissionResDTO.CompletedMissionDTO.builder()
                .storeName(memberMission.getStore().getName())
                .missionContent(memberMission.getMission().getContent())
                .missionPoint(memberMission.getMission().getPoint())
                .status(memberMission.getStatus())
                .build();
    }
}
