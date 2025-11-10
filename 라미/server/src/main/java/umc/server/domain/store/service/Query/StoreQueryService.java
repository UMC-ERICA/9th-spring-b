package umc.server.domain.store.service.Query;

import umc.server.domain.store.dto.Res.StoreResDTO;

public interface StoreQueryService {

    StoreResDTO.StorePageDTO searchStores(String regionName, String keyword, String sortBy, int page, int size);

    StoreResDTO.StoreCursorDTO searchStoresByCursor(String regionName, String keyword, String sortBy, Long cursor, int size);
}

