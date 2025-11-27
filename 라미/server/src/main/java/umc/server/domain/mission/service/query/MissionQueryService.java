package umc.server.domain.mission.service.query;

import umc.server.domain.mission.dto.res.MissionResDTO;

public interface MissionQueryService {

    MissionResDTO.StoreMissionListDTO findMission(Long StoreId, Integer page);

    MissionResDTO.MyMissionListDTO findMyMissions(Long memberId, Integer page);
}
