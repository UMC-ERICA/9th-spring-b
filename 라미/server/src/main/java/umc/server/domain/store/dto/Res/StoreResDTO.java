package umc.server.domain.store.dto.Res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.server.domain.store.entity.Store;

import java.time.LocalDateTime;
import java.util.List;

public class StoreResDTO {

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