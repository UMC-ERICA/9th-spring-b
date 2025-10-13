package umc.server.domain.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.server.domain.mission.entity.MemberMission;
import umc.server.domain.mission.enums.MissionStatus;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query("""
        select mm
        from MemberMission mm
        join mm.mission m
        where mm.member.id = :memberId and mm.missionStatus = :status
    """)
    Page<MemberMission> findPageByMemberIdAndStatus(@Param("memberId") Long memberId, @Param("status") MissionStatus status, Pageable pageable);
}
