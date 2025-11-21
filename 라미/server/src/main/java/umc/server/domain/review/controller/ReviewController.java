package umc.server.domain.review.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.review.converter.ReviewConverter;
import umc.server.domain.review.dto.req.ReviewReqDTO;
import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.service.command.ReviewCommandService;
import umc.server.domain.review.service.query.ReviewQueryServiceImpl;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryServiceImpl reviewQueryServiceImpl;
    private final ReviewCommandService reviewCommandService;

    // 내가 작성한 리뷰 보기
    @GetMapping("/reviews/search")
    public ApiResponse<ReviewResDTO.MyReviews> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ){
        List<Review> reviews = reviewQueryServiceImpl.searchReview(query, type);
        ReviewResDTO.MyReviews result = ReviewConverter.toMyReviewsDTO(reviews);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 가게에 리뷰 추가하기
    @PostMapping("/stores/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewDTO> createReview(
            @PathVariable Long storeId,
            @Valid @RequestBody ReviewReqDTO.CreateDTO dto
    ) {
        Review review = reviewCommandService.createReview(dto);
        ReviewResDTO.CreateReviewDTO result = ReviewConverter.toCreateReviewDTO(review);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, result);
    }
}
