package umc.server.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.member.entity.MemberFood;

public interface MemberFoodRepository extends JpaRepository<MemberFood, Long> {
}
