package umc.server.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.RequestParam;
import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.global.annotation.ValidPage;
import umc.server.global.apiPayload.ApiResponse;

// Swagger 전용 Docs
public interface ReviewControllerDocs {

    @Operation( // API 설명 작성 (제목, 설명)
            summary = "가게의 리뷰 목록 조회 API",
            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({ // @ApiResponse를 묶음
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"), // 코드와 응답 메시지 작성
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getStoreReviews(
            @RequestParam String storeName,
            @RequestParam  @Min(1) Integer page
    );

    @Operation(
            summary = "내가 작성한 리뷰 목록 조회 API ",
            description = "내가 작성한 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"), // 코드와 응답 메시지 작성
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<ReviewResDTO.MyReviewListDTO> getMyReviews(
            @RequestParam(defaultValue = "1") @ValidPage Integer page
    );
}