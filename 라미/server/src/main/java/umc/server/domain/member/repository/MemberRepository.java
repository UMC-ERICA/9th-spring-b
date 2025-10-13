package umc.server.domain.member.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import umc.server.domain.member.dto.MyMissionDto;
import umc.server.domain.member.dto.MyPageDto;
import umc.server.domain.member.entity.Member;
import umc.server.domain.mission.MissionStatus;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("""
           SELECT new umc.server.domain.member.dto.MyPageDto(m.name, m.email, m.point)
           FROM Member m
           WHERE m.id = :memberId
           """)
    MyPageDto findMyPageInfoById(@Param("memberId") Long memberId);

    @Query("""
           SELECT new umc.server.domain.member.dto.MyMissionDto(mm.id, m.content, s.name, m.point, mm.status, mm.createdAt)
           FROM MemberMission mm
           JOIN mm.mission m
           JOIN mm.store s
           WHERE mm.member.id = :memberId AND mm.status IN :statuses
           """)
    Page<MyMissionDto> findMyMissions(@Param("memberId") Long memberId,
                                      @Param("statuses") List<MissionStatus> statuses,
                                      Pageable pageable);

}
