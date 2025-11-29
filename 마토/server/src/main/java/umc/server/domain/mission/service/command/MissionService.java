package umc.server.domain.mission.service.command;

import umc.server.domain.mission.dto.req.MissionChallengeReqDto;
import umc.server.domain.mission.dto.req.MissionReqDto;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.entity.MissionChallenge;

public interface MissionService {
    // 미션 생성
    Mission addMission(Long storeId, MissionReqDto.JoinDTO dto);

    // 미션 조회
    Mission findMissionById(Long missionId);

    // 도전미션 생성
    MissionChallenge challengeMission(Long missionId, MissionChallengeReqDto.ChallengeMissionDTO dto);
}
