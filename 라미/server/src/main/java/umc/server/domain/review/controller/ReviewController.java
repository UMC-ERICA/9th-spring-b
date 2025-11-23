package umc.server.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.mission.enums.MissionStatus;
import umc.server.domain.mission.exception.code.MissionSuccessCode;
import umc.server.domain.review.converter.ReviewConverter;
import umc.server.domain.review.dto.req.ReviewReqDTO;
import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.exception.code.ReviewSuccessCode;
import umc.server.domain.review.service.command.ReviewCommandService;
import umc.server.domain.review.service.query.ReviewQueryService;
import umc.server.domain.review.service.query.ReviewQueryServiceImpl;
import umc.server.global.annotation.ValidPage;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class ReviewController implements ReviewControllerDocs {

    private final ReviewQueryServiceImpl reviewQueryServiceImpl;
    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

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

    // 가게의 리뷰 목록 조회
    @GetMapping("/reviews")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getStoreReviews(
            @RequestParam String storeName,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        ReviewSuccessCode code = ReviewSuccessCode.REVIEW_LIST_FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findReview(storeName, page));
    }

    // 내가 작성한 리뷰 목록 (페이징 포함)
    @GetMapping("/reviews/me")
    public ApiResponse<ReviewResDTO.MyReviewListDTO> getMyReviews(
            @RequestParam(defaultValue = "1") Integer page
    ) {
        Long TEMP_MEMBER_ID = 1L; // JWT 구현 후에는 토큰에서 추출

        ReviewSuccessCode code = ReviewSuccessCode.REVIEW_LIST_FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findMyReview(TEMP_MEMBER_ID, page));
    }
}
