package umc.server.domain.review.service;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.review.entity.QReview;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.enums.RatingLevel;
import umc.server.domain.review.repository.ReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public List<Review> searchReview(Long memberId, Long storeId, RatingLevel ratingLevel) {

        // Q클래스 정의
        QReview review = QReview.review;

        // BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        // BooleanBuilder 사용

        builder.and(review.member.id.eq(memberId));

        if (storeId != null) {
            builder.and(review.store.id.eq(storeId));
        }

        if (ratingLevel != null) {
            switch (ratingLevel) {
                case FIVE -> builder.and(review.rating.goe(5.0f));
                case FOUR -> builder.and(review.rating.goe(4.0f).and(review.rating.lt(5.0f)));
                case THREE -> builder.and(review.rating.goe(3.0f).and(review.rating.lt(4.0f)));
                case TWO -> builder.and(review.rating.goe(2.0f).and(review.rating.lt(3.0f)));
                case ONE -> builder.and(review.rating.goe(1.0f).and(review.rating.lt(2.0f)));
            }
        }

        // Repository 사용 & 결과 매핑
        List<Review> reviewList = reviewRepository.searchReview(builder);
        return reviewList;
    }
}

