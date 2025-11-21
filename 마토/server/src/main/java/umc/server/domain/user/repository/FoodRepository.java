package umc.server.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.user.entity.Food;

import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    Optional<Food> findByName(String name);
}
