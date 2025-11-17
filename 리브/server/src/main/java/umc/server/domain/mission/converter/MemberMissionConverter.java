package umc.server.domain.mission.converter;

import umc.server.domain.member.entity.Member;
import umc.server.domain.mission.dto.res.MemberMissionResDTO;
import umc.server.domain.mission.entity.MemberMission;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.enums.MissionStatus;

public class MemberMissionConverter {

    // entity -> DTO
    public static MemberMissionResDTO.ChallengeResponse toChallengeResponseDTO(MemberMission memberMission) {
        return MemberMissionResDTO.ChallengeResponse.builder()
                .missionStatus(memberMission.getMissionStatus())
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getId())
                .memberId(memberMission.getId())
                .build();
    }

    // Member + Mission -> MemberMission entity
    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .missionStatus(MissionStatus.IN_PROGRESS)
                .build();
    }
}
