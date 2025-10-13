package umc.server.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.server.domain.review.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 리뷰 작성 쿼리(insert)는 save() 메서드가 이미 JpaRepository에 구현되어 있으므로
    // 따로 메서드 작성하지 않았습니다.
}
