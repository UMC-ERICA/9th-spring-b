package umc.server.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.enums.MemberStatus;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByIdAndMemberStatus(Long id, MemberStatus memberStatus);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("update Member m set m.username = :username, m.updatedAt = CURRENT_TIMESTAMP where m.id = :id")
    int updateUsername(@Param("id") Long id, @Param("username") String username);
}
