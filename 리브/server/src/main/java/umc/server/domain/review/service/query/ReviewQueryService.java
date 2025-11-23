package umc.server.domain.review.service.query;

import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.enums.RatingLevel;

import java.util.List;

public interface ReviewQueryService {

    List<Review> searchReview(Long memberId, Long storeId, RatingLevel ratingLevel) throws Exception;

    List<Review> searchReview(String filter, String type) throws Exception;

    ReviewResDTO.ReviewPreViewListDTO findReview(String storeName, Integer page);
}

