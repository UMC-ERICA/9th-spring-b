package umc.server.domain.review.service.command;

import umc.server.domain.review.dto.req.ReviewReqDTO;
import umc.server.domain.review.entity.Review;

public interface ReviewCommandService {

    Review createReview(ReviewReqDTO.CreateDTO request);
}
