package umc.server.domain.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.store.dto.Req.StoreReqDTO;
import umc.server.domain.store.dto.Res.StoreResDTO;
import umc.server.domain.store.service.Query.StoreQueryServiceImpl;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreQueryServiceImpl storeQueryServiceImpl;

    // 가게 검색 기능
    @GetMapping("/search")
    public ApiResponse<StoreResDTO.StorePageDTO> searchStores(@Valid StoreReqDTO.SearchDTO request) { // DTO 유효성 검사 실행
        StoreResDTO.StorePageDTO result = storeQueryServiceImpl.searchStores(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result );
    }

    // 커서 기반 검색
    @GetMapping("/search/cursor")
    public ApiResponse<StoreResDTO.StoreCursorDTO> searchStoresByCursor(@Valid StoreReqDTO.SearchCursorDTO request) {
        StoreResDTO.StoreCursorDTO result = storeQueryServiceImpl.searchStoresByCursor(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}