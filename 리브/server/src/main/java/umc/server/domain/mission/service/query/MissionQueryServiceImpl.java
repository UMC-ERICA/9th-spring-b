package umc.server.domain.mission.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.repository.MissionRepository;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;

    @Transactional(readOnly = true)
    public Page<Mission> getChallengeableMissions(Long memberId, String storeAddress, Pageable pageable) {
        return missionRepository.findChallengeableMissions(memberId, storeAddress, pageable);
    }
}
