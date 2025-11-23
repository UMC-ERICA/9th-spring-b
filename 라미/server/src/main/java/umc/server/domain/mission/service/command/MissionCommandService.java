package umc.server.domain.mission.service.command;

import umc.server.domain.member.entity.mapping.MemberMission;
import umc.server.domain.mission.dto.req.MissionReqDTO;
import umc.server.domain.mission.dto.res.MissionResDTO;

public interface MissionCommandService {

    MemberMission challengeMission(Long storeId, MissionReqDTO.ChallengeDTO request);

    MissionResDTO.CompletedMissionDTO completeMission(Long memberMissionId);
}
