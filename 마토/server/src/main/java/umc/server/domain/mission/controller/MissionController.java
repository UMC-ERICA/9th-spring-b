package umc.server.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.mission.dto.req.MissionReqDTO;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.service.MissionService;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.repository.StoreRepository;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mission")
public class MissionController {

    private final MissionService missionService;
    private final StoreRepository storeRepository;

    @PostMapping("/{storeId}")
    public ResponseEntity<ApiResponse<?>> addMissionToStore(
            @PathVariable Long storeId, // URL에서 가게 ID를 받음
            @RequestBody  MissionReqDTO.JoinDTO missionReqDTO // 미션 정보를 받음
    ) {
        // 가게를 확인
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        // 가게에 미션을 추가
        Mission mission = missionService.addMission(store, missionReqDTO);

        // 성공적인 처리 후 응답 반환
        return ResponseEntity.ok(ApiResponse.onSuccess(GeneralSuccessCode.CREATED, mission));
    }
}
