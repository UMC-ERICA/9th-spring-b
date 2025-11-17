package umc.server.domain.store.converter;

import umc.server.domain.store.dto.req.StoreReqDTO;
import umc.server.domain.store.dto.res.StoreResDTO;
import umc.server.domain.store.entity.Store;

public class StoreConverter {

    // entity -> DTO
    public static StoreResDTO.CreateStoreResponse toCreateStoreResponseDTO(Store store) {
        return StoreResDTO.CreateStoreResponse.builder()
                .storeId(store.getId())
                .storeName(store.getStoreName())
                .locationName(store.getLocationName())
                .storeAddress(store.getStoreAddress())
                .category(store.getCategory())
                .build();
    }

    // DTO -> entity
    public static Store toStore(String locationName, StoreReqDTO.CreateStoreRequest dto) {
        return Store.builder()
                .storeName(dto.storeName())
                .locationName(locationName)
                .storeAddress(dto.storeAddress())
                .category(dto.category())
                .build();
    }
}
