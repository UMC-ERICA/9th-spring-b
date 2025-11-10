package umc.server.domain.store.converter;

import org.springframework.data.domain.Page;
import umc.server.domain.store.dto.Res.StoreResDTO;
import umc.server.domain.store.entity.Store;

import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    // 객체 -> DTO

    // 단일 Store 엔티티 -> StoreDTO
    public static StoreResDTO.StoreDTO toStoreDTO(Store store) {
        return StoreResDTO.StoreDTO.builder()
                .id(store.getId())
                .name(store.getName())
                .regionName(store.getRegion() != null ? store.getRegion().getName() : null)
                .phoneNum(store.getPhoneNum())
                .restaurantCategory(store.getRestaurantCategory().name())
                .createdAt(store.getCreatedAt())
                .build();
    }

    // Store 엔티티 리스트 -> StoreDTO 리스트
    public static List<StoreResDTO.StoreDTO> toStoreDTOList(List<Store> stores) {
        return stores.stream()
                .map(StoreConverter::toStoreDTO)
                .collect(Collectors.toList());
    }

    // 커서 기반 페이지네이션 응답 DTO로 변환
    public static StoreResDTO.StoreCursorDTO toStoreCursorDTO(List<Store> stores, boolean hasNext) {
        List<StoreResDTO.StoreDTO> content = toStoreDTOList(stores);

        Long nextCursor = hasNext && !stores.isEmpty()
                ? stores.get(stores.size() - 1).getId()
                : null;

        return StoreResDTO.StoreCursorDTO.builder()
                .content(content)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }

    // 페이지네이션 응답 DTO로 변환
    public static StoreResDTO.StorePageDTO toStorePageDTO(Page<Store> storePage, int page, int size) {
        List<StoreResDTO.StoreDTO> content = toStoreDTOList(storePage.getContent());

        return StoreResDTO.StorePageDTO.builder()
                .content(content)
                .page(page)
                .size(size)
                .totalElements(storePage.getTotalElements())
                .totalPages(storePage.getTotalPages())
                .hasNext(storePage.hasNext())
                .build();
    }
}
