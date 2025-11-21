package umc.server.domain.review.converter;

import org.springframework.data.domain.Page;
import umc.server.domain.member.entity.Member;
import umc.server.domain.review.dto.req.ReviewReqDTO;
import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.store.entity.Store;

import java.time.LocalDate;
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

    public static ReviewResDTO.ReviewPreViewListDTO toReviewPreViewListDTO(Page<Review> result) {
        return ReviewResDTO.ReviewPreViewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewPreViewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResDTO.ReviewPreViewDTO toReviewPreViewDTO(Review review) {
        return ReviewResDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getRating())
                .body(review.getContent())
                .crateAt(LocalDate.from(review.getCreatedAt())) // 날짜 부분만
                .build();
    }

    // DTO -> 객체
    public static Review toReview(ReviewReqDTO.CreateDTO dto, Member member, Store store) {
        return Review.builder()
                .content(dto.getContent())
                .rating(dto.getRating())
                .member(member)
                .store(store)
                .build();
    }

}
