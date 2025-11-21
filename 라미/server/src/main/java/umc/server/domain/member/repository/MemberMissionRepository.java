package umc.server.domain.member.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import umc.server.domain.member.entity.mapping.MemberMission;
import umc.server.domain.mission.enums.MissionStatus;
import umc.server.domain.member.dto.ChallengingMissionDto;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query(value = """
           SELECT new umc.server.domain.member.dto.ChallengingMissionDto(m.id, m.content, m.point, m.deadline, s.name, mm.status)
           FROM MemberMission mm
           JOIN mm.mission m
           JOIN m.store s
           JOIN s.region r
           WHERE mm.member.id = :memberId AND r.id = :regionId AND mm.status = :status
           """,
            countQuery = "SELECT count(mm) FROM MemberMission mm JOIN mm.mission m JOIN m.store s JOIN s.region r WHERE mm.member.id = :memberId AND r.id = :regionId AND mm.status = :status")
    Page<ChallengingMissionDto> findChallengingMissions(@Param("memberId") Long memberId,
                                                        @Param("regionId") Long regionId,
                                                        @Param("status") MissionStatus status,
                                                        Pageable pageable);


}
