package umc.server.domain.store.service;

import umc.server.domain.store.dto.req.StoreReqDTO;
import umc.server.domain.store.entity.Store;

public interface StoreService {

    Store addStore(StoreReqDTO.JoinDTO storeRequestDTO);
}
