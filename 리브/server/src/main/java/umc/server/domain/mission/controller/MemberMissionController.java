package umc.server.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.mission.dto.res.MemberMissionResDTO;
import umc.server.domain.mission.service.command.MemberMissionCommandService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberMissionController {

    private final MemberMissionCommandService memberMissionCommandService;

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
}
