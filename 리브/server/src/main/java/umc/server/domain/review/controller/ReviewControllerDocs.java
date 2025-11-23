package umc.server.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestParam;
import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.global.apiPayload.ApiResponse;

public interface ReviewControllerDocs {

    @Operation(
            summary = "가게의 리뷰 목록 조회 API",
            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam Integer page
    );
}
