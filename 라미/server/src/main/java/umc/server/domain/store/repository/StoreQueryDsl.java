package umc.server.domain.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.server.domain.store.entity.Store;

import java.util.List;

public interface StoreQueryDsl {
    // 페이징 검색
    Page<Store> searchStores(
            String regionName,
            String keyword,
            String sortBy,
            Pageable pageable
    );

    // 커서 기반 검색 (선택)
    List<Store> searchStoresByCursor(
            String regionName,
            String keyword,
            String sortBy,
            Long cursor,
            int size
    );
}