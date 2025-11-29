package umc.server.domain.mission.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.mission.converter.MissionChallengeConverter;
import umc.server.domain.mission.dto.req.MissionChallengeReqDto;
import umc.server.domain.mission.dto.res.MissionChallengeResDto;
import umc.server.domain.mission.entity.MissionChallenge;
import umc.server.domain.mission.service.command.MissionService;
import umc.server.domain.mission.service.query.MissionQueryService;
import umc.server.global.annotation.Page;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
@Tag(name = "Mission Challenge", description = "도전미션 및 진행중 미션 조회 API")
public class MissionChallengeController {

    private final MissionService missionService;
    private final MissionQueryService missionQueryService;
    private final MissionChallengeConverter missionChallengeConverter;

    @PostMapping("/{missionId}/challenges")
    @Operation(summary = "미션 도전 시작", description = "특정 미션에 대해 사용자가 도전 시작")
    public ApiResponse<MissionChallengeResDto.ChallengeDTO> challengeMission(
            @PathVariable Long missionId,               // URL에서 미션 ID를 받음
            @RequestBody @Valid MissionChallengeReqDto.ChallengeMissionDTO challengeReqDTO  // 도전 정보
    ) {
        MissionChallenge missionChallenge = missionService.challengeMission(missionId, challengeReqDTO);

        var dto = missionChallengeConverter.toChallengeDTO(missionChallenge);
        // 성공적인 처리 후 응답 반환
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, dto);
    }

    @GetMapping("/users/{userId}/challenges/in-progress")
    @Operation(
            summary = "내가 진행중인 미션 목록 조회",
            description = "특정 사용자가 현재 진행중인 미션 도전 목록을 페이지네이션하여 조회"
    )
    public ApiResponse<MissionChallengeResDto.ChallengePreviewListDTO> getMyInProgressMissions(
            @PathVariable Long userId,
            @RequestParam @Page Integer page
    ) {
        int pageIndex = page - 1;

        var challengePage = missionQueryService.findInProgressByUser(userId, pageIndex);
        var dto = missionChallengeConverter.toChallengePreviewListDTO(challengePage);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, dto);
    }
}
