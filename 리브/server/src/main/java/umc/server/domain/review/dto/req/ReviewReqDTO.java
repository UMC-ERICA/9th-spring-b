package umc.server.domain.review.dto.req;

public class ReviewReqDTO {

    public record CreateReviewRequest(
            Long memberId,
            String comment,
            float rating
    ) {}
}
