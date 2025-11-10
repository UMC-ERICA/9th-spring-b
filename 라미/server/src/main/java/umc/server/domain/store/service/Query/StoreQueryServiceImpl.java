package umc.server.domain.store.service.Query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.store.dto.Res.StoreResDTO;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.repository.StoreRepository;
import umc.server.domain.store.converter.StoreConverter;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;

    // 페이징 검색
    @Override
    public StoreResDTO.StorePageDTO searchStores(
            String regionName,
            String keyword,
            String sortBy,
            int page,
            int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Store> storePage = storeRepository.searchStores(
                regionName,
                keyword,
                sortBy,
                pageable
        );
        return StoreConverter.toStorePageDTO(storePage, page, size);
    }

    // 커서 기반 검색
    @Override
    public StoreResDTO.StoreCursorDTO searchStoresByCursor(
            String regionName,
            String keyword,
            String sortBy,
            Long cursor,
            int size
    ) {
        List<Store> stores = storeRepository.searchStoresByCursor(
                regionName,
                keyword,
                sortBy,
                cursor,
                size
        );

        boolean hasNext = stores.size() > size;

        if (hasNext) {
            stores.remove(stores.size() - 1);
        }
        return StoreConverter.toStoreCursorDTO(stores, hasNext);
    }
}