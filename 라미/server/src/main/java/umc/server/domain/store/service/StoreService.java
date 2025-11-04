package umc.server.domain.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.store.dto.StoreResponseDTO;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.repository.StoreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;

    // 페이징 검색
    public StoreResponseDTO.StorePageDTO searchStores(
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

        List<StoreResponseDTO.StoreDTO> content = storePage.getContent().stream()
                .map(StoreResponseDTO.StoreDTO::from)
                .collect(Collectors.toList());

        return StoreResponseDTO.StorePageDTO.builder()
                .content(content)
                .page(page)
                .size(size)
                .totalElements(storePage.getTotalElements())
                .totalPages(storePage.getTotalPages())
                .hasNext(storePage.hasNext())
                .build();
    }

    // 커서 기반 검색
    public StoreResponseDTO.StoreCursorDTO searchStoresByCursor(
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

        List<StoreResponseDTO.StoreDTO> content = stores.stream()
                .map(StoreResponseDTO.StoreDTO::from)
                .collect(Collectors.toList());

        Long nextCursor = hasNext && !stores.isEmpty()
                ? stores.get(stores.size() - 1).getId()
                : null;

        return StoreResponseDTO.StoreCursorDTO.builder()
                .content(content)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }
}