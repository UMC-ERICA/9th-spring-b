package umc.server.domain.review.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

public class ReviewDto {
    private Long id;
    private String content;
    private Float rating;
    private Long storeId;
    private String storeName;
    private LocalDateTime createdAt;
}
