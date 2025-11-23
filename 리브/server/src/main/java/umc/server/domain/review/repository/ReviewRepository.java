package umc.server.domain.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.server.domain.member.enums.FoodType;
import umc.server.domain.review.entity.Review;
import umc.server.domain.store.entity.Store;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {

    Page<Review> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Page<Review> findAllByStore(Store store, PageRequest pageRequest);
}
