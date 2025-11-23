package umc.server.domain.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.mission.entity.MissionChallenge;

public interface MissionChallengeRepository extends JpaRepository<MissionChallenge, Long> {
    MissionChallenge findByUserIdAndMissionId(Long userId, Long missionId);
}
