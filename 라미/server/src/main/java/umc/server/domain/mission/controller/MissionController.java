package umc.server.domain.mission.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.member.entity.mapping.MemberMission;
import umc.server.domain.mission.converter.MissionConverter;
import umc.server.domain.mission.dto.req.MissionReqDTO;
import umc.server.domain.mission.dto.res.MissionResDTO;
import umc.server.domain.mission.enums.MissionStatus;
import umc.server.domain.mission.exception.code.MissionSuccessCode;
import umc.server.domain.mission.service.command.MissionCommandService;
import umc.server.domain.mission.service.query.MissionQueryService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@Validated
public class MissionController implements MissionControllerDocs {
    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    // 가게의 미션 도전하기
    @PostMapping("/stores/{storeId}/missions/challenge")
    public ApiResponse<MissionResDTO.ChallengeDTO> challengeMission(
            @PathVariable Long storeId,
            @Valid @RequestBody MissionReqDTO.ChallengeDTO request
    ) {
        MemberMission memberMission = missionCommandService.challengeMission(storeId, request);
        MissionResDTO.ChallengeDTO result = MissionConverter.toChallengeDTO(memberMission);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, result);
    }

    // 특정 가게의 미션 목록 조회
    @GetMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.StoreMissionListDTO> getMissions(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        MissionSuccessCode code = MissionSuccessCode.MISSION_LIST_FOUND;
        return ApiResponse.onSuccess(code, missionQueryService.findMission(storeId, page));
    }

    // 진행 중인 미션 진행 완료로 바꾸기
    @PatchMapping("/member-missions/{memberMissionId}/complete")
    public ApiResponse<MissionResDTO.CompletedMissionDTO> completeMission(
            @PathVariable Long memberMissionId
    ) {
        MissionSuccessCode code = MissionSuccessCode.MISSION_FOUND;
        return ApiResponse.onSuccess(code, missionCommandService.completeMission(memberMissionId));
    }

    // 내가 진행중인 미션 목록 조회
    @GetMapping("/member-missions/me")
    public ApiResponse<MissionResDTO.MyMissionListDTO> getMyMissions(
            @RequestParam(defaultValue = "1") Integer page
    ) {
        Long TEMP_MEMBER_ID = 1L; // JWT 구현 후에는 토큰에서 추출

        MissionSuccessCode code = MissionSuccessCode.MISSION_LIST_FOUND;
        return ApiResponse.onSuccess(code, missionQueryService.findMyMissions(TEMP_MEMBER_ID, page));
    }
}
