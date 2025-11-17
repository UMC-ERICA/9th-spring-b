package umc.server.domain.mission.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.mission.converter.MissionConverter;
import umc.server.domain.mission.dto.req.MissionReqDTO;
import umc.server.domain.mission.dto.res.MissionResDTO;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.repository.MissionRepository;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.exception.StoreException;
import umc.server.domain.store.exception.code.StoreErrorCode;
import umc.server.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    // 가게에 미션 추가
    @Override
    @Transactional
    public MissionResDTO.CreateMissionResponse createMission(Long storeId, MissionReqDTO.CreateMissionRequest dto) {
        // Store 조회 (없으면 예외)
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        // Mission 엔티티 생성
        Mission mission = MissionConverter.toMission(store, dto);

        // 저장
        Mission saved = missionRepository.save(mission);

        // DTO 변환 후 반환
        return MissionConverter.toCreateMissionResponseDTO(saved);
    }


}
