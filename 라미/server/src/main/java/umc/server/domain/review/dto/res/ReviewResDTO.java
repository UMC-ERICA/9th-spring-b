package umc.server.domain.review.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
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

    @Builder
    public record ReviewPreViewDTO(
            String ownerNickname,
            Float score,
            String body,
            LocalDate crateAt
    ){}

    // 특정 페이지의 리뷰 목록 + 페이징 정보
    @Builder
    public record ReviewPreViewListDTO(
            List<ReviewPreViewDTO> reviewList, // 현재 페이지에 포함된 리뷰 목록
            Integer listSize, // 현재 페이지에 담긴 리뷰 개수(reviewList.size())
            Integer totalPage, // 전체 페이지 수
            Long totalElements, // 전체 리뷰 개수(모든 페이지 합)
            Boolean isFirst,
            Boolean isLast
    ){}
}
