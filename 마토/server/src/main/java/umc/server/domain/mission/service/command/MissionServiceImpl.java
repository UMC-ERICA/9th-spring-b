package umc.server.domain.mission.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.mission.dto.req.MissionChallengeReqDto;
import umc.server.domain.mission.dto.req.MissionReqDto;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.entity.MissionChallenge;
import umc.server.domain.mission.repository.MissionChallengeRepository;
import umc.server.domain.store.entity.Store;
import umc.server.domain.mission.repository.MissionRepository;
import umc.server.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MissionChallengeRepository missionChallengeRepository;

    @Override
    public Mission addMission(Long storeId, MissionReqDto.JoinDTO dto) {


        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));


        Mission mission = Mission.builder()
                .store(store)
                .endDate(dto.endDate())
                .conditional(dto.conditional())
                .point(dto.point())
                .build();

        return missionRepository.save(mission);
    }

    @Override
    public Mission findMissionById(Long missionId) {
        return missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("Mission not found"));
    }

    @Override
    public MissionChallenge challengeMission(Long missionId, MissionChallengeReqDto.ChallengeMissionDTO dto) {
        Mission mission = findMissionById(missionId);


        MissionChallenge missionChallenge = MissionChallenge.builder()
                .mission(mission)
                .status("IN_PROGRESS")   // 도전 미션 생성 시 IN_PROGRESS로 시작
                .userId(dto.userId())
                .build();

        return missionChallengeRepository.save(missionChallenge);
    }
}
