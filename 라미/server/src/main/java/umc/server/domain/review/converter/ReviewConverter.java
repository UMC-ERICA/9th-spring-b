package umc.server.domain.review.converter;

import umc.server.domain.member.entity.Member;
import umc.server.domain.review.dto.req.ReviewReqDTO;
import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.store.entity.Store;

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

    public static ReviewResDTO.CreateReviewDTO toCreateReviewDTO(Review review) {
        return ReviewResDTO.CreateReviewDTO.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .storeName(review.getStore().getName())
                .memberId(review.getMember().getId())
                .memberName(review.getMember().getName())
                .title(review.getTitle())
                .content(review.getContent())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // DTO -> 객체
    public static Review toReview(ReviewReqDTO.CreateDTO dto, Member member, Store store) {
        return Review.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .rating(dto.getRating())
                .member(member)
                .store(store)
                .build();
    }

}
