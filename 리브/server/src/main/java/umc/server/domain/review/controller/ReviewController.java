package umc.server.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.enums.RatingLevel;
import umc.server.domain.review.service.ReviewQueryService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/reviews/me")
    public List<Review> searchReview(
            @RequestParam Long memberId,
            @RequestParam Long storeId,
            @RequestParam RatingLevel ratingLevel
    ) {
        // 서비스에게 요청
        List<Review> result = reviewQueryService.searchReview(memberId, storeId, ratingLevel);
        return result;
    }
}
