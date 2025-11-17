package umc.server.domain.store.dto.res;

import lombok.Builder;

public class StoreResDTO {

    @Builder
    public record CreateStoreResponse(
            Long storeId,
            String storeName,
            String locationName,
            String storeAddress,
            String category
    ) {}
}
