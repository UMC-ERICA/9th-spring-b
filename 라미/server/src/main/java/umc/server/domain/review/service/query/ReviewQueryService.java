package umc.server.domain.review.service.query;

import umc.server.domain.review.entity.Review;

import java.util.List;

public interface ReviewQueryService {

    List<Review> searchReview(String query, String type);
}
