package umc.server.domain.mission.service.command;

import umc.server.domain.mission.dto.req.MissionReqDTO;
import umc.server.domain.mission.dto.res.MissionResDTO;

public interface MissionCommandService {

    // 가게에 미션 추가
    MissionResDTO.CreateMissionResponse createMission(Long storeId, MissionReqDTO.CreateMissionRequest dto);
}
