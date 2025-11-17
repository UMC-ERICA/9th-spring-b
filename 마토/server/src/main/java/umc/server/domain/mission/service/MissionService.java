package umc.server.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.mission.dto.req.MissionChallengeReqDTO;
import umc.server.domain.mission.dto.req.MissionReqDTO;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.entity.MissionChallenge;
import umc.server.domain.mission.repository.MissionChallengeRepository;
import umc.server.domain.store.entity.Store;
import umc.server.domain.mission.repository.MissionRepository;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final MissionChallengeRepository missionChallengeRepository;


    // 미션을 ID로 찾는 메서드 추가
    public Mission findMissionById(Long missionId) {
        return missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("Mission not found"));
    }

    @Transactional
    public Mission addMission(Store store, MissionReqDTO.JoinDTO missionReqDTO) {


        // 미션 생성
        Mission mission = Mission.builder()
                .store(store)
                .endDate(missionReqDTO.endDate())
                .conditional(missionReqDTO.conditional())
                .point(missionReqDTO.point())
                .build();

        mission = missionRepository.save(mission);

        // 미션을 도전하는 기본 상태를 "진행 중"으로 설정
        MissionChallenge missionChallenge = MissionChallenge.builder()
                .mission(mission)
                .status("진행 중")
                .userId(1L)
                .build();

        // 미션 도전 상태 저장
        missionChallengeRepository.save(missionChallenge);

        // 생성된 미션 반환
        return mission;
    }

    // 미션 도전 상태를 생성하는 메서드
    @Transactional
    public MissionChallenge challengeMission(Mission mission, MissionChallengeReqDTO.JoinDTO challengeReqDTO) {
        // 미션 도전 상태 생성
        MissionChallenge missionChallenge = MissionChallenge.builder()
                .mission(mission)
                .status("진행 중")
                .userId(challengeReqDTO.userId())  // 요청 DTO에서 유저 ID 가져오기
                .build();


        return missionChallengeRepository.save(missionChallenge);
    }
}
