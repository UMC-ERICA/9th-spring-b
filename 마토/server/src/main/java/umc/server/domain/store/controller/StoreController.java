package umc.server.domain.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.store.converter.StoreConverter;
import umc.server.domain.store.dto.req.StoreReqDTO;
import umc.server.domain.store.entity.Region;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.repository.RegionRepository;
import umc.server.domain.store.service.StoreService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final RegionRepository regionRepository;

    @PostMapping("/{regionId}/stores")
    public ResponseEntity<ApiResponse<?>> addStore(
            @PathVariable Long regionId,               // URL에서 지역 ID를 받음
            @RequestBody @Valid StoreReqDTO.JoinDTO storeReqDTO  // 요청 본문에서 가게 정보 받음
    ) {
        // regionId로 지역을 찾습니다.
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new RuntimeException("Region not found"));

        // StoreConverter를 사용하여 DTO -> Entity 변환
        Store store = StoreConverter.toStore(storeReqDTO, region);

        // 가게 추가 처리
        var savedStore = storeService.addStore(storeReqDTO);

        // 성공적인 처리 후 응답 반환
        return ResponseEntity.ok(ApiResponse.onSuccess(GeneralSuccessCode.CREATED, savedStore));
    }
}
