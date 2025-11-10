package umc.server.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.review.converter.ReviewConverter;
import umc.server.domain.review.dto.ReviewDto;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.service.ReviewQueryService;
import umc.server.domain.review.service.ReviewService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewQueryService reviewQueryService;
    private final ReviewConverter reviewConverter;

    //리뷰 생성
    @PostMapping
    public ApiResponse<ReviewDto> createReview(
            @RequestParam Long userId,
            @RequestParam Long missionId,
            @RequestParam String content,
            @RequestParam Float rating
    ) {
        var review = reviewService.createReview(userId, missionId, content, rating);
        var response = reviewConverter.toReviewDto(review);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    //리뷰 검색
    @GetMapping("/search")
    public ApiResponse<List<ReviewDto>> searchReviews(
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Float rating
    ) {
        var reviews = reviewQueryService.searchReview(storeId, rating);
        var result = reviews.stream()
                .map(reviewConverter::toReviewDto)
                .toList();

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
