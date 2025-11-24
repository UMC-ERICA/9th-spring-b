package umc.server.domain.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.server.domain.mission.entity.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    @Query("""
        SELECT m
        FROM Mission m
        JOIN m.store s
        WHERE s.address LIKE CONCAT('%', :region, '%')
        AND m.id NOT IN (
            SELECT um.mission.id
            FROM UserMission um
            WHERE um.user.id = :userId
        )
    """)
    Page<Mission> findAvailableMissionsByRegion(
            @Param("userId") Long userId,
            @Param("region") String region,
            Pageable pageable
    );

    Page<Mission> findByStore_Id(Long storeId, Pageable pageable);

}
