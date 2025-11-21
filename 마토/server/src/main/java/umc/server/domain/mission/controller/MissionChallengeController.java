package umc.server.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.mission.dto.req.MissionChallengeReqDTO;
import umc.server.domain.mission.entity.MissionChallenge;
import umc.server.domain.mission.service.MissionService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;
import umc.server.domain.mission.entity.Mission;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mission")
public class MissionChallengeController {

    private final MissionService missionService;

    @PostMapping("/{missionId}/challenges")
    public ResponseEntity<ApiResponse<?>> challengeMission(
            @PathVariable Long missionId,               // URL에서 미션 ID를 받음
            @RequestBody MissionChallengeReqDTO.JoinDTO challengeReqDTO  // 도전 정보
    ) {
        // 미션이 존재하는지 확인
        Mission mission = missionService.findMissionById(missionId);

        // 미션 도전 상태 생성
        MissionChallenge missionChallenge = missionService.challengeMission(mission, challengeReqDTO);

        // 성공적인 처리 후 응답 반환
        return ResponseEntity.ok(ApiResponse.onSuccess(GeneralSuccessCode.CREATED, missionChallenge));
    }
}
