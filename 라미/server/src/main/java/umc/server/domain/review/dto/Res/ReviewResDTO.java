package umc.server.domain.review.dto.Res;

import lombok.Builder;
import lombok.Getter;
import umc.server.domain.review.entity.Review;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Getter
    @Builder
    public static class MyReviews {
        private List<MyReview> reviews;
    }

    @Getter
    @Builder
    public static class MyReview {
        private Long id;
        private String title;
        private String content;
        private Float rating;
        private Long storeId;
        private LocalDateTime createdAt;
    }
}
