package umc.server.domain.store.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.store.converter.StoreConverter;
import umc.server.domain.store.dto.req.StoreReqDTO;
import umc.server.domain.store.dto.res.StoreResDTO;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;

    // 특정 지역에 가게 추가
    @Override
    @Transactional
    public StoreResDTO.CreateStoreResponse createStore(String locationName, StoreReqDTO.CreateStoreRequest dto) {
        // Store 엔티티 생성
        Store store = StoreConverter.toStore(locationName, dto);

        // 저장
        Store saved = storeRepository.save(store);

        // DTO 변환 후 반환
        return StoreConverter.toCreateStoreResponseDTO(saved);
    }
}
