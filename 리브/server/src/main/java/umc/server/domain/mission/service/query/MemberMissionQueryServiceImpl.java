package umc.server.domain.mission.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.mission.entity.MemberMission;
import umc.server.domain.mission.enums.MissionStatus;
import umc.server.domain.mission.repository.MemberMissionRepository;

@Service
@RequiredArgsConstructor
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {

    private final MemberMissionRepository memberMissionRepository;

    @Transactional(readOnly = true)
    public Page<MemberMission> getMemberMissions(Long memberId, MissionStatus status, Pageable pageable) {
        return memberMissionRepository.findPageByMemberIdAndStatus(memberId, status, pageable);
    }
}
