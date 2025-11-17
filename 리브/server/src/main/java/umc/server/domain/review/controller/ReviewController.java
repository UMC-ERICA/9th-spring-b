package umc.server.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.review.converter.ReviewConverter;
import umc.server.domain.review.dto.req.ReviewReqDTO;
import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.enums.RatingLevel;
import umc.server.domain.review.service.command.ReviewCommandService;
import umc.server.domain.review.service.query.ReviewQueryService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewQueryService reviewQueryService;  // 조회용
    private final ReviewCommandService reviewCommandService;  // 쓰기용

    // 내가 작성한 리뷰 조회
    @GetMapping("/me")
    public ApiResponse<List<ReviewResDTO.CreateReviewResponse>> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam Long storeId,
            @RequestParam RatingLevel ratingLevel
    ) {
        // 1. 서비스에서 엔티티 조회
        List<Review> reviews = reviewQueryService.searchReview(memberId, storeId, ratingLevel);

        // 2. Converter로 DTO 변환
        List<ReviewResDTO.CreateReviewResponse> reviewInfoDTO = ReviewConverter.toReviewInfoList(reviews);

        // 3. 통일된 응답으로 반환
        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewInfoDTO);
    }

    // 가게에 리뷰 추가
    @PostMapping("/stores/{storeId}")
    public ApiResponse<ReviewResDTO.CreateReviewResponse> addReview(
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.CreateReviewRequest dto
    ) {
        // 1. 서비스에서 DTO까지 만들어서 반환
        ReviewResDTO.CreateReviewResponse result = reviewCommandService.createReview(storeId, dto);

        // 2. 통일된 응답으로 반환
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
