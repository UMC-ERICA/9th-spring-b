package umc.server.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.review.converter.ReviewConverter;
import umc.server.domain.review.dto.ReviewResDto;
import umc.server.domain.review.exception.code.ReviewSuccessCode;
import umc.server.domain.review.service.query.ReviewQueryService;
import umc.server.domain.review.service.command.ReviewService;
import umc.server.global.annotation.Page;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewQueryService reviewQueryService;
    private final ReviewConverter reviewConverter;

    //리뷰 생성
    @PostMapping("/reviews")
    public ApiResponse<ReviewResDto> createReview(
            @RequestParam Long userId,
            @RequestParam Long storeId,
            @RequestParam String content,
            @RequestParam Float rating
    ) {
        var review = reviewService.createReview(userId, storeId, content, rating);
        var response = reviewConverter.toReviewDto(review);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    //리뷰 검색
    @GetMapping("/search")
    public ApiResponse<List<ReviewResDto>> searchReviews(
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Float rating
    ) {
        var reviews = reviewQueryService.searchReview(storeId, rating);
        var result = reviews.stream()
                .map(reviewConverter::toReviewDto)
                .toList();

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 가게의 리뷰 목록 조회
    @GetMapping("/reviews")
    public ApiResponse<ReviewResDto.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam Integer page

    ){

        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findReview(storeName, page));
    }

    @GetMapping("/users/{userId}/reviews")
    @Operation(summary = "내가 작성한 리뷰 목록 조회", description = "사용자의 리뷰를 페이지네이션하여 조회합니다.")
    public ApiResponse<ReviewResDto.ReviewPreViewListDTO> getMyReviews(
            @PathVariable Long userId,
            @RequestParam @Page Integer page         // page 검증
    ) {
        int pageIndex = page - 1;

        var reviewPage = reviewQueryService.findMyReviews(userId, pageIndex);
        var dto = reviewConverter.toReviewPreviewListDTO(reviewPage);

        return ApiResponse.onSuccess(ReviewSuccessCode.FOUND, dto);
    }
}
