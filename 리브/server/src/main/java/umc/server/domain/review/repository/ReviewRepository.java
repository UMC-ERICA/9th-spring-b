package umc.server.domain.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.review.entity.Review;
import umc.server.domain.store.entity.Store;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {

    Page<Review> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Page<Review> findAllByStore(Store store, Pageable pageRequest);

    Page<Review> findAllByMemberId(Long memberId, Pageable pageable);
}
