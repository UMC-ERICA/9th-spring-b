package umc.server.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.review.converter.ReviewConverter;
import umc.server.domain.review.dto.req.ReviewReqDTO;
import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.enums.RatingLevel;
import umc.server.domain.review.exception.code.ReviewSuccessCode;
import umc.server.domain.review.service.command.ReviewCommandService;
import umc.server.domain.review.service.query.ReviewQueryService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController implements ReviewControllerDocs {

    private final ReviewQueryService reviewQueryService;  // 조회용
    private final ReviewCommandService reviewCommandService;  // 쓰기용

    // 내가 작성한 리뷰 목록 필터링해서 조회
    @GetMapping("/search/me")
    public ApiResponse<List<ReviewResDTO.CreateReviewResponse>> searchMyReview(
            @RequestParam Long memberId,
            @RequestParam Long storeId,
            @RequestParam RatingLevel ratingLevel
    ) throws Exception {
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

    // 가게의 리뷰 목록 필터링해서 조회
    @GetMapping("/search")
    public ApiResponse<List<ReviewResDTO.CreateReviewResponse>> searchReview(
            @RequestParam String filter,   // 가게 이름
            @RequestParam String type      // 음식 종류 (양식, 중식 등)
    ) throws Exception {
        // 1. 서비스에서 엔티티 조회
        List<Review> reviews = reviewQueryService.searchReview(filter, type);

        // 2. 엔티티 -> DTO 변환 (이미 만들어 둔 Converter 재사용)
        List<ReviewResDTO.CreateReviewResponse> dtoList = ReviewConverter.toReviewInfoList(reviews);

        // 3. 통일된 응답으로 반환
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, dtoList);
    }

    // 가게의 리뷰 목록 조회
    @GetMapping
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findReview(storeName, page));
    }

    // 내가 작성한 리뷰 목록 조회
    @GetMapping("/me")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findMyReview(memberId, page));
    }
}
