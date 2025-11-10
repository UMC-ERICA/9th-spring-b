package umc.server.domain.review.converter;

import org.springframework.stereotype.Component;
import umc.server.domain.review.dto.ReviewDto;
import umc.server.domain.review.entity.Review;

@Component
public class ReviewConverter {

    public ReviewDto toReviewDto(Review review) {
        return new ReviewDto(
                review.getId(),
                review.getContent(),
                review.getRating(),
                review.getStore().getId(),
                review.getStore().getName(),
                review.getCreatedAt()
        );
    }
}
