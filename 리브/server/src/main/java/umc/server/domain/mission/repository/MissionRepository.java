package umc.server.domain.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.server.domain.mission.entity.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("""
        select m
        from Mission m
        join m.store s
        left join m.memberMissionList mm on mm.mission = m and mm.member.id = :memberId
        where m.deadline >= CURRENT_DATE
          and s.storeAddress = :storeAddress
          and not exists (
            select 1
            from MemberMission mm2
            where mm2.mission = m
              and mm2.member.id = :memberId
              and mm2.missionStatus = umc.server.domain.mission.enums.MissionStatus.COMPLETED
          )
        order by m.deadline asc, m.id desc
    """)
    Page<Mission> findChallengeableMissions(@Param("memberId") Long memberId, @Param("storeAddress") String storeAddress, Pageable pageable);
}
