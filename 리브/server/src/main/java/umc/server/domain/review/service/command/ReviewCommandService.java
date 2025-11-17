package umc.server.domain.review.service.command;

import umc.server.domain.review.dto.req.ReviewReqDTO;
import umc.server.domain.review.dto.res.ReviewResDTO;

public interface ReviewCommandService {

    // 가게에 리뷰 추가
    ReviewResDTO.CreateReviewResponse createReview(Long storeId, ReviewReqDTO.CreateReviewRequest dto);
}
