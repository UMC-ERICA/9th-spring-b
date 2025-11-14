package umc.server.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.review.converter.ReviewConverter;
import umc.server.domain.review.dto.Res.ReviewResDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.service.Query.ReviewQueryServiceImpl;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewQueryServiceImpl reviewQueryServiceImpl;

    // 내가 작성한 리뷰 보기
    @GetMapping("/search")
    public ApiResponse<ReviewResDTO.MyReviews> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ){
        List<Review> reviews = reviewQueryServiceImpl.searchReview(query, type);
        ReviewResDTO.MyReviews result = ReviewConverter.toMyReviewsDTO(reviews);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
