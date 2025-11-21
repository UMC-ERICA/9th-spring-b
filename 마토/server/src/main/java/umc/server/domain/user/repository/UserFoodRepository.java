package umc.server.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.mapping.entitiy.UserFood;

public interface UserFoodRepository extends JpaRepository<UserFood, Long> {
}
