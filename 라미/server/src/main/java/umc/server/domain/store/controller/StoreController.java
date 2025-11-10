package umc.server.domain.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ApiResponse<StoreResDTO.StorePageDTO> searchStores(
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "latest") String sort,  // latest or name
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        StoreResDTO.StorePageDTO result = storeQueryServiceImpl.searchStores(
                region,
                keyword,
                sort,
                page,
                size
        );
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 커서 기반 검색
    @GetMapping("/search/cursor")
    public ApiResponse<StoreResDTO.StoreCursorDTO> searchStoresByCursor(
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "latest") String sort,
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "10") int size
    ) {
        StoreResDTO.StoreCursorDTO result = storeQueryServiceImpl.searchStoresByCursor(
                region,
                keyword,
                sort,
                cursor,
                size
        );
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}