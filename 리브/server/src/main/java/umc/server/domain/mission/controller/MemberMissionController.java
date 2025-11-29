package umc.server.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.mission.dto.res.MemberMissionResDTO;
import umc.server.domain.mission.service.command.MemberMissionCommandService;
import umc.server.domain.mission.service.query.MemberMissionQueryService;
import umc.server.domain.review.exception.code.ReviewSuccessCode;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;
import umc.server.global.validation.ValidPage;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Validated
public class MemberMissionController implements MemberMissionControllerDocs {

    private final MemberMissionCommandService memberMissionCommandService;
    private final MemberMissionQueryService memberMissionQueryService;

    // 가게의 미션을 도전 중인 미션에 추가
    @PostMapping("/{memberId}/missions/{missionId}/challenge")
    public ApiResponse<MemberMissionResDTO.ChallengeResponse> ChallengeMission(
            @PathVariable Long memberId,
            @PathVariable Long missionId
    ) {
        // 1. 서비스에서 DTO까지 만들어서 반환
        MemberMissionResDTO.ChallengeResponse result = memberMissionCommandService.challengeMission(memberId, missionId);

        // 2. 통일된 응답으로 반환
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 내가 진행중인 미션 목록 조회
    @GetMapping("/{memberId}/missions/ongoing")
    public ApiResponse<MemberMissionResDTO.MyMissionPreViewListDTO> getMyOngoingMission(
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "1") @ValidPage Integer page
    ) {
        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, memberMissionQueryService.findMyOngoingMissions(memberId, page));
    }
}
