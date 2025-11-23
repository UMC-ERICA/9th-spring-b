package umc.server.domain.review.converter;

import umc.server.domain.member.entity.Member;
import umc.server.domain.review.dto.req.ReviewReqDTO;
import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.store.entity.Store;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    // entity -> DTO
    public static ReviewResDTO.CreateReviewResponse toCreateReviewResponseDTO(Review review) {
        return ReviewResDTO.CreateReviewResponse.builder()
                .reviewId(review.getId())
                .comment(review.getComment())
                .rating(review.getRating())
                .memberId(review.getMember().getId())
                .storeId(review.getStore().getId())
                .build();
    }

    public static List<ReviewResDTO.CreateReviewResponse> toReviewInfoList(List<Review> reviews) {
        return reviews.stream()
                .map(review -> ReviewConverter.toCreateReviewResponseDTO(review))
                .collect(Collectors.toList());
    }

    // DTO -> entity
    public static Review toReview(Member member, Store store, ReviewReqDTO.CreateReviewRequest dto) {
        return Review.builder()
                .member(member)
                .store(store)
                .comment(dto.comment())
                .rating(dto.rating())
                .build();
    }
}
