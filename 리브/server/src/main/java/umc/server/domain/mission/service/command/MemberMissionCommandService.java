package umc.server.domain.mission.service.command;

import umc.server.domain.mission.dto.res.MemberMissionResDTO;

public interface MemberMissionCommandService {

    // 가게의 미션을 도전 중인 미션에 추가
    MemberMissionResDTO.ChallengeResponse challengeMission(Long memberId, Long missionId);
}
