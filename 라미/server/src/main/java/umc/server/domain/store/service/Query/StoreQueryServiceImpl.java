package umc.server.domain.store.service.Query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.store.dto.req.StoreReqDTO;
import umc.server.domain.store.dto.res.StoreResDTO;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.repository.StoreRepository;
import umc.server.domain.store.converter.StoreConverter;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;

    // 페이징 검색
    @Override
    public StoreResDTO.StorePageDTO searchStores(StoreReqDTO.SearchDTO request) {

        String region = request.getRegion();
        String keyword = request.getKeyword();
        String sort = request.getSort();
        int page = request.getPage();
        int size = request.getSize();
        Pageable pageable = PageRequest.of(page, size);

        Page<Store> storePage = storeRepository.searchStores(
                region,
                keyword,
                sort,
                pageable
        );
        return StoreConverter.toStorePageDTO(storePage, page, size);
    }

    // 커서 기반 검색
    @Override
    public StoreResDTO.StoreCursorDTO searchStoresByCursor(StoreReqDTO.SearchCursorDTO request) {

        String region = request.getRegion();
        String keyword = request.getKeyword();
        String sort = request.getSort();
        Long cursor = request.getCursor();
        int size = request.getSize();

        List<Store> stores = storeRepository.searchStoresByCursor(
                region,
                keyword,
                sort,
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