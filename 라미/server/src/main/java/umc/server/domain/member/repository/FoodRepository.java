package umc.server.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.server.domain.member.entity.Food;
import umc.server.domain.member.enums.FoodCategory;

import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    Optional<Food> findByCategory(FoodCategory category);
}
