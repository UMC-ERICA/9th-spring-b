package umc.server.domain.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.server.domain.store.entity.Store;

import java.time.LocalDateTime;
import java.util.List;

public class StoreResponseDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StoreDTO {
        private Long id;
        private String name;
        private String regionName;
        private String phoneNum;
        private String restaurantCategory;
        private LocalDateTime createdAt;

        public static StoreDTO from(Store store) {
            return StoreDTO.builder()
                    .id(store.getId())
                    .name(store.getName())
                    .regionName(store.getRegion() != null ? store.getRegion().getName() : null)
                    .phoneNum(store.getPhoneNum())
                    .restaurantCategory(store.getRestaurantCategory().name())
                    .createdAt(store.getCreatedAt())
                    .build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StorePageDTO {
        private List<StoreDTO> content;
        private int page;
        private int size;
        private long totalElements;
        private int totalPages;
        private boolean hasNext;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StoreCursorDTO {
        private List<StoreDTO> content;
        private Long nextCursor;
        private boolean hasNext;
    }
}