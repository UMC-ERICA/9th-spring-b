package umc.server.domain.review.dto.res;

import lombok.Builder;

public class ReviewResDTO {

    @Builder
    public record CreateReviewResponse(
            Long reviewId,
            String comment,
            float rating,
            Long memberId,
            Long storeId
    ) {}
}
