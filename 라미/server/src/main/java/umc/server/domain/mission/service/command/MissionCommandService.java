package umc.server.domain.mission.service.command;

import umc.server.domain.member.entity.mapping.MemberMission;
import umc.server.domain.mission.dto.req.MissionReqDTO;

public interface MissionCommandService {
    MemberMission challengeMission(Long storeId, MissionReqDTO.ChallengeDTO request);
}
