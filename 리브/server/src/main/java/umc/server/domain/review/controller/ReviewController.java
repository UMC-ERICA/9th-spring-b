package umc.server.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.review.converter.ReviewConverter;
import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.enums.RatingLevel;
import umc.server.domain.review.service.ReviewQueryService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.BaseSuccessCode;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/me")
    public ApiResponse<List<ReviewResDTO.ReviewInfo>> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam Long storeId,
            @RequestParam RatingLevel ratingLevel
    ) {
        // 1. 서비스에서 엔티티 조회
        List<Review> reviews = reviewQueryService.searchReview(memberId, storeId, ratingLevel);

        // 2. Converter로 DTO 변환
        List<ReviewResDTO.ReviewInfo> reviewInfoDTO = ReviewConverter.toReviewInfoList(reviews);

        // 3. 통일된 응답으로 반환
        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewInfoDTO);
    }
}
