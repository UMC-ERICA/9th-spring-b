package umc.server.domain.review.converter;

import org.springframework.data.domain.Page;
import umc.server.domain.member.entity.Member;
import umc.server.domain.review.dto.req.ReviewReqDTO;
import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.store.entity.Store;

import java.time.LocalDate;
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

    // entity 리스트 -> DTO 리스트
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

    // Page<Review> -> ReviewList DTO
    public static ReviewResDTO.ReviewPreViewListDTO toReviewPreviewListDTO(Page<Review> result) {
        return ReviewResDTO.ReviewPreViewListDTO.builder()
                .reviewList(result.getContent().stream()
                                .map(ReviewConverter::toReviewPreviewDTO)
                                .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    // Review -> Preview DTO (요약 정보)
    public static ReviewResDTO.ReviewPreViewDTO toReviewPreviewDTO(Review review) {
        return ReviewResDTO.ReviewPreViewDTO.builder()
                .nickname(review.getMember().getUsername())
                .rating(review.getRating())
                .comment(review.getComment())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }
}
