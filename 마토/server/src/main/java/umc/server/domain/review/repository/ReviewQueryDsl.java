package umc.server.domain.review.repository;

import com.querydsl.core.types.Predicate;
import umc.server.domain.review.entity.Review;

import java.util.List;

public interface ReviewQueryDsl {
    List<Review> searchReview(
            Predicate predicate
    );
}
