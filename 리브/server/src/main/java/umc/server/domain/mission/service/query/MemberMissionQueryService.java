package umc.server.domain.mission.service.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.server.domain.mission.entity.MemberMission;
import umc.server.domain.mission.enums.MissionStatus;

public interface MemberMissionQueryService {

    Page<MemberMission> getMemberMissions(Long memberId, MissionStatus status, Pageable pageable);
}
