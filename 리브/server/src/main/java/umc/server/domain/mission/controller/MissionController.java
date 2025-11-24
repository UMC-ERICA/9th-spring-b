package umc.server.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.mission.dto.req.MissionReqDTO;
import umc.server.domain.mission.dto.res.MissionResDTO;
import umc.server.domain.mission.exception.code.MissionSuccessCode;
import umc.server.domain.mission.service.command.MissionCommandService;
import umc.server.domain.mission.service.query.MissionQueryService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;
import umc.server.global.validation.ValidPage;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
@Validated
public class MissionController implements MissionControllerDocs {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    // 가게에 미션 추가
    @PostMapping("/{storeId}/missions")
    public ApiResponse<MissionResDTO.CreateMissionResponse> addMission(
            @PathVariable Long storeId,
            @RequestBody MissionReqDTO.CreateMissionRequest dto
    ) {
        // 1. 서비스에서 DTO까지 만들어서 반환
        MissionResDTO.CreateMissionResponse result = missionCommandService.createMission(storeId, dto);

        // 2. 통일된 응답으로 반환
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 특정 가게의 미션 목록 조회
    @GetMapping("/{storeId}/missions/challengeable")
    public ApiResponse<MissionResDTO.MissionPreViewListDTO> getStoreMissions(
            @PathVariable Long storeId,
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "1") @ValidPage Integer page
    ) {
        MissionSuccessCode code = MissionSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, missionQueryService.findStoreMissions(memberId, storeId, page));
    }
}
