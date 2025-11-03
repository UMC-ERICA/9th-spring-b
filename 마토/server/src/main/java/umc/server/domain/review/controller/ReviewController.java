package umc.server.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.service.ReviewQueryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewQueryService reviewQueryService;

    @GetMapping("/search")
    public List<Review> searchReview(
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Float rating
    ) {
        return reviewQueryService.searchReview(storeId, rating);
    }
}
