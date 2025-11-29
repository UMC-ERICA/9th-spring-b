package umc.server.domain.review.dto.res;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record CreateReviewResponse(
            Long reviewId,
            String comment,
            float rating,
            Long memberId,
            Long storeId
    ) {}

    @Builder
    public record ReviewPreViewListDTO(
            List<ReviewPreViewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record ReviewPreViewDTO(
            String nickname,
            String comment,
            float rating,
            LocalDate createdAt
    ) {}
}
