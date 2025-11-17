package umc.server.domain.review.converter;

import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.domain.review.entity.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewConverter {

    // 객체 -> DTO

    // 개별 리뷰 변환
    public static ReviewResDTO.MyReview toMyReviewDTO(Review review) {
        return ReviewResDTO.MyReview.builder()
                .id(review.getId())
                .title(review.getTitle())
                .content(review.getContent())
                .rating(review.getRating())
                .storeId(review.getStore().getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // 리뷰 리스트 반환
    public static ReviewResDTO.MyReviews toMyReviewsDTO(List<Review> reviews) {
        List<ReviewResDTO.MyReview> reviewList = new ArrayList<>();
        for (Review review : reviews) {
            ReviewResDTO.MyReview dto = ReviewConverter.toMyReviewDTO(review);
            reviewList.add(dto);
        }
        return ReviewResDTO.MyReviews.builder()
                .reviews(reviewList)
                .build();
    }

}
