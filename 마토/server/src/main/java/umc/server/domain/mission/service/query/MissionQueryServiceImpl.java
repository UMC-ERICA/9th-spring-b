package umc.server.domain.mission.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.entity.MissionChallenge;
import umc.server.domain.mission.repository.MissionChallengeRepository;
import umc.server.domain.mission.repository.MissionRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService{
    private final MissionRepository missionRepository;
    private final MissionChallengeRepository missionChallengeRepository;

    @Override
    public Page<Mission> findStoreMissions(Long storeId, int pageIndex) {
        return missionRepository.findByStore_Id(
                storeId,
                PageRequest.of(
                        pageIndex,
                        10, // 한 페이지 10개
                        Sort.by(Sort.Direction.ASC, "endDate")
                )
        );
    }

    private static final String STATUS_IN_PROGRESS = "IN_PROGRESS";

    @Override
    public Page<MissionChallenge> findInProgressByUser(Long userId, int pageIndex) {
        Pageable pageable = PageRequest.of(pageIndex, 10); // 한 페이지 10개
        return missionChallengeRepository.findByUserIdAndStatus(userId, STATUS_IN_PROGRESS, pageable);
    }
}
