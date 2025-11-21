package umc.server.domain.mission.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.member.entity.mapping.MemberMission;
import umc.server.domain.mission.converter.MissionConverter;
import umc.server.domain.mission.dto.req.MissionReqDTO;
import umc.server.domain.mission.dto.res.MissionResDTO;
import umc.server.domain.mission.service.command.MissionCommandService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
public class MissionController {
    private final MissionCommandService missionCommandService;

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
}
