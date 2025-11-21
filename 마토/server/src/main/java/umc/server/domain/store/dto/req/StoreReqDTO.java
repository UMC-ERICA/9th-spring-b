package umc.server.domain.store.dto.req;

import jakarta.validation.constraints.NotNull;

public class StoreReqDTO {

    public record JoinDTO(
            String storeName,
            String address,
            String detailAddress,
            @NotNull
            Long regionId
    ) {}
}
