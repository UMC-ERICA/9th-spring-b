package umc.server.domain.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.store.dto.req.StoreReqDTO;
import umc.server.domain.store.dto.res.StoreResDTO;
import umc.server.domain.store.service.command.StoreCommandService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regions")
public class StoreController {

    private final StoreCommandService storeCommandService;

    // 특정 지역에 가게 추가
    @PostMapping("/{locationName}/stores")
    public ApiResponse<StoreResDTO.CreateStoreResponse> addStore(
            @PathVariable String locationName,
            @RequestBody StoreReqDTO.CreateStoreRequest dto
    ) {
        // 1. 서비스에서 DTO까지 만들어서 반환
        StoreResDTO.CreateStoreResponse result = storeCommandService.createStore(locationName, dto);

        // 2. 통일된 응답으로 반환
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
