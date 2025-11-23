package umc.server.domain.store.service.command;

import umc.server.domain.store.dto.req.StoreReqDTO;
import umc.server.domain.store.dto.res.StoreResDTO;

public interface StoreCommandService {

    // 특정 지역에 가게 추가
    StoreResDTO.CreateStoreResponse createStore(String locationName, StoreReqDTO.CreateStoreRequest dto);
}
