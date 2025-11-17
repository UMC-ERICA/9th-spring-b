package umc.server.domain.mission.service.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.server.domain.mission.entity.Mission;

public interface MissionQueryService {

    Page<Mission> getChallengeableMissions(Long memberId, String storeAddress, Pageable pageable);
}
