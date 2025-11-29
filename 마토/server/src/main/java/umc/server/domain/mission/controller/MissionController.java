package umc.server.domain.mission.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.mission.converter.MissionConverter;
import umc.server.domain.mission.dto.req.MissionReqDto;
import umc.server.domain.mission.dto.res.MissionResDto;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.service.command.MissionService;
import umc.server.domain.mission.service.query.MissionQueryService;
import umc.server.domain.store.repository.StoreRepository;
import umc.server.global.annotation.Page;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;


@RestController
@RequiredArgsConstructor
@Validated
@Tag(name = "Mission", description = "미션 생성 및 조회 API")
@RequestMapping("/api/mission")
public class MissionController {

    private final MissionService missionService;
    private final StoreRepository storeRepository;
    private final MissionQueryService missionQueryService;
    private final MissionConverter missionConverter;

    @PostMapping("/stores/{storeId}")
    @Operation(summary = "가게에 미션 생성", description = "특정 가게(storeId)에 새로운 미션을 등록합니다.")
    public ApiResponse<MissionResDto.MissionPreviewDTO> addMissionToStore(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionReqDto.JoinDTO missionReqDTO
    ) {
        Mission mission = missionService.addMission(storeId, missionReqDTO);

        MissionResDto.MissionPreviewDTO dto = missionConverter.toPreviewDTO(mission);

        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, dto);
    }

    // 특정 가게의 미션 목록 조회
    @GetMapping("/stores/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회")
    public ApiResponse<MissionResDto.MissionPreviewListDTO> getStoreMissions(
            @PathVariable Long storeId,
            @RequestParam @Page Integer page
    ) {
        int pageIndex = page - 1;

        var missionPage = missionQueryService.findStoreMissions(storeId, pageIndex);
        var dto = missionConverter.toPreviewListDTO(missionPage);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, dto);
    }
}
