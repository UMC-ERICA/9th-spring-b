package umc.server.domain.mission.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import umc.server.domain.mission.dto.res.MissionResDTO;
import umc.server.domain.mission.exception.code.MissionSuccessCode;
import umc.server.global.annotation.ValidPage;
import umc.server.global.apiPayload.ApiResponse;

// Swagger 전용 Docs
public interface MissionControllerDocs {

    @Operation(
            summary = "가게의 미션 목록 조회 API",
            description = "특정 가게의 미션을 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<MissionResDTO.StoreMissionListDTO> getMissions(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "1") @ValidPage Integer page
    );

    @Operation(
            summary = "진행 중인 미션 진행 완료로 변경 API",
            description = "진행 중인 미션의 상태를 진행 완료로 변경합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<MissionResDTO.CompletedMissionDTO> completeMission(
            @PathVariable Long memberMissionId
    );

    @Operation(
            summary = "내가 진행 중인 미션 목록 조회 API",
            description = "내가 진행 중인 미션 목록을 모두 조회합니다. 페이지네이션으로 제공됩니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<MissionResDTO.MyMissionListDTO> getMyMissions(
            @RequestParam(defaultValue = "1") @ValidPage Integer page
    );
}
