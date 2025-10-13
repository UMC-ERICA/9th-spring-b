package umc.server.domain.mapping.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.mapping.entitiy.UserMission;

import java.util.Optional;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    Page<UserMission> findByUserIdAndIsCompletedTrue(Long userId, Pageable pageable);

    Page<UserMission> findByUserIdAndIsCompletedFalse(Long userId, Pageable pageable);

    Optional<UserMission> findByUserIdAndMissionMissionId(Long userId, Long missionId);
}
