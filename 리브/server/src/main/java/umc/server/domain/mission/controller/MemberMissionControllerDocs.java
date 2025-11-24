package umc.server.domain.mission.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestParam;
import umc.server.domain.mission.dto.res.MemberMissionResDTO;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.validation.ValidPage;

public interface MemberMissionControllerDocs {

    @Operation(
            summary = "내가 진행중인 미션 목록 조회 API",
            description = "내가 진행중인 미션을 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<MemberMissionResDTO.MyMissionPreViewListDTO> getMyOngoingMission(
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "1") @ValidPage Integer page
    );
}
