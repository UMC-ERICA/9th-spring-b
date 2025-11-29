package umc.server.domain.mission.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.mission.converter.MissionConverter;
import umc.server.domain.mission.dto.res.MissionResDTO;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.repository.MissionRepository;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.exception.StoreException;
import umc.server.domain.store.exception.code.StoreErrorCode;
import umc.server.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Mission> getChallengeableMissions(Long memberId, String storeAddress, Pageable pageable) {
        return missionRepository.findChallengeableMissions(memberId, storeAddress, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public MissionResDTO.MissionPreViewListDTO findStoreMissions(Long memberId, Long storeId, Integer page) {

        // 가게를 가져온다 (가게 존재 여부 검증)
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));
        String storeAddress = store.getStoreAddress();

        // 가게에 맞는 미션을 가져온다 (Offset 페이징)
        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        Page<Mission> result = missionRepository.findChallengeableMissions(memberId, storeAddress, pageRequest);

        // 결과를 응답 DTO로 변환한다 (컨버터 이용)
        return MissionConverter.toMissionPreViewListDTO(result);
    }
}
