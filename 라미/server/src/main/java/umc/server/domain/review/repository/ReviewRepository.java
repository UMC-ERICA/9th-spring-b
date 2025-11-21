package umc.server.domain.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.server.domain.review.entity.Review;
import umc.server.domain.store.entity.Store;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {

    Page<Review> findAllByStore(Store store, PageRequest pageRequest);
}
