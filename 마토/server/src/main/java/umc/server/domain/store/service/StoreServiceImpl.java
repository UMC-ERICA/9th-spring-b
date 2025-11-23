package umc.server.domain.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.store.converter.StoreConverter;
import umc.server.domain.store.dto.req.StoreReqDTO;
import umc.server.domain.store.entity.Region;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.repository.RegionRepository;
import umc.server.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;   // 클래스 수준에서 선언
    private final RegionRepository regionRepository; // 클래스 수준에서 선언

    @Transactional
    @Override
    public Store addStore(StoreReqDTO.JoinDTO storeRequestDTO) {
        // StoreReqDTO에서 받은 regionId로 Region 조회
        Region region = regionRepository.findById(storeRequestDTO.regionId())
                .orElseThrow(() -> new RuntimeException("Region not found"));

        // StoreConverter를 사용하여 StoreReqDTO를 Store 엔티티로 변환
        Store store = StoreConverter.toStore(storeRequestDTO, region);

        // Store 엔티티를 DB에 저장
        return storeRepository.save(store);
    }
}