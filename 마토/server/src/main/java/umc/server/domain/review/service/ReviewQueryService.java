package umc.server.domain.review.service;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import umc.server.domain.review.entity.QReview;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.repository.ReviewQueryDsl;
import umc.server.domain.review.repository.ReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewQueryDsl reviewQueryDsl;

    public List<Review> searchReview(Long storeId, Float rating) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        if (review.reviewId != null) {
            builder.and(review.store.id.eq(storeId));
        }


        if (review.rating != null) {
            float lowerBound = (float) Math.floor(rating);
            float upperBound = lowerBound + 1.0f;
            builder.and(review.rating.goe(lowerBound));
            builder.and(review.rating.lt(upperBound));
        }


        List<Review> reviewList = reviewQueryDsl.searchReview(builder);
        return reviewList;
    }

}
