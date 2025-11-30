package umc.server.domain.review.converter;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import umc.server.domain.review.dto.ReviewResDto;
import umc.server.domain.review.entity.Review;

import java.time.LocalDate;

@Component
public class ReviewConverter {

    public ReviewResDto toReviewDto(Review review) {
        return new ReviewResDto(
                review.getId(),
                review.getContent(),
                review.getRating(),
                review.getStore().getId(),
                review.getStore().getName(),
                review.getCreatedAt()
        );
    }

    // result -> DTO
    public static ReviewResDto.ReviewPreViewListDTO toReviewPreviewListDTO(
            Page<Review> result
    ){
        return ReviewResDto.ReviewPreViewListDTO.builder()
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

    public static ReviewResDto.ReviewPreViewDTO toReviewPreviewDTO(
            Review review
    ){
        return ReviewResDto.ReviewPreViewDTO.builder()
                .ownerNickname(review.getUser().getName())
                .score(review.getRating())
                .body(review.getContent())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }
}

