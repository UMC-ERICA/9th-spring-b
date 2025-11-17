package umc.server.domain.store.dto.req;

public class StoreReqDTO {

    public record CreateStoreRequest(
            String storeName,
            String locationName,
            String storeAddress,
            String category
    ) {}
}
