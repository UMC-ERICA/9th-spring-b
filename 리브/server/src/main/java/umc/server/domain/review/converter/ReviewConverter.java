package umc.server.domain.review.converter;

import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.domain.review.entity.Review;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    // 객체 -> DTO
    public static ReviewResDTO.ReviewInfo toReviewInfoDTO(Review review) {
        return ReviewResDTO.ReviewInfo.builder()
                .reviewId(review.getId())
                .comment(review.getComment())
                .rating(review.getRating())
                .memberId(review.getMember().getId())
                .storeId(review.getStore().getId())
                .build();
    }

    public static List<ReviewResDTO.ReviewInfo> toReviewInfoList(List<Review> reviews) {
        return reviews.stream()
                .map(review -> ReviewConverter.toReviewInfoDTO(review))
                .collect(Collectors.toList());
    }
}
