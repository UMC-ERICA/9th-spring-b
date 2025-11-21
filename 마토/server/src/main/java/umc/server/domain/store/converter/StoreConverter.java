package umc.server.domain.store.converter;

import umc.server.domain.store.dto.req.StoreReqDTO;
import umc.server.domain.store.entity.Region;
import umc.server.domain.store.entity.Store;

public class StoreConverter {
    public static Store toStore(StoreReqDTO.JoinDTO storeReqDTO, Region region) {
        // DTO에서 받은 데이터를 기반으로 Store 엔티티 생성
        return Store.builder()
                .name(storeReqDTO.storeName())
                .address(storeReqDTO.address())
                .detailAddress(storeReqDTO.detailAddress())
                .region(region)
                .build();
    }
}
