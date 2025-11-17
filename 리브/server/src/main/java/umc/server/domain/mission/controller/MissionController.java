package umc.server.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.mission.dto.req.MissionReqDTO;
import umc.server.domain.mission.dto.res.MissionResDTO;
import umc.server.domain.mission.service.command.MissionCommandService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class MissionController {

    private final MissionCommandService missionCommandService;

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
}
