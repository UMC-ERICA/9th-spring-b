package umc.server.domain.mission.service.query;

import org.springframework.data.domain.Page;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.entity.MissionChallenge;

public interface MissionQueryService {

    Page<Mission> findStoreMissions(Long storeId, int pageIndex);

    Page<MissionChallenge> findInProgressByUser(Long userId, int pageIndex);
}
