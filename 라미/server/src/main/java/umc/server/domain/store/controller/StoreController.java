package umc.server.domain.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.store.dto.StoreResponseDTO;
import umc.server.domain.store.service.StoreService;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    // 페이징 검색
    @GetMapping("/search")
    public ResponseEntity<StoreResponseDTO.StorePageDTO> searchStores(
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "latest") String sort,  // latest or name
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        StoreResponseDTO.StorePageDTO result = storeService.searchStores(
                region,
                keyword,
                sort,
                page,
                size
        );
        return ResponseEntity.ok(result);
    }

    // 커서 기반 검색
    @GetMapping("/search/cursor")
    public ResponseEntity<StoreResponseDTO.StoreCursorDTO> searchStoresByCursor(
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "latest") String sort,
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "10") int size
    ) {
        StoreResponseDTO.StoreCursorDTO result = storeService.searchStoresByCursor(
                region,
                keyword,
                sort,
                cursor,
                size
        );
        return ResponseEntity.ok(result);
    }
}