package umc.server.domain.review.dto.res;

import lombok.Builder;
import lombok.Getter;

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

    @Getter
    @Builder
    public static class CreateReviewDTO {
        private Long reviewId;
        private Long storeId;
        private String storeName;
        private Long memberId;
        private String memberName;
        private String title;
        private String content;
        private Float rating;
        private LocalDateTime createdAt;
    }
}
