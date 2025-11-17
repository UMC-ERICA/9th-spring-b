package umc.server.domain.store.service.Query;

import umc.server.domain.store.dto.req.StoreReqDTO;
import umc.server.domain.store.dto.res.StoreResDTO;

public interface StoreQueryService {

    StoreResDTO.StorePageDTO searchStores(StoreReqDTO.SearchDTO request);

    StoreResDTO.StoreCursorDTO searchStoresByCursor(StoreReqDTO.SearchCursorDTO request);
}

