package umc.server.domain.review.dto.res;

import lombok.Builder;
import lombok.Getter;

public class ReviewResDTO {

    @Builder
    @Getter
    public static class ReviewInfo {
        private Long reviewId;
        private String comment;
        private float rating;
        private Long memberId;
        private Long storeId;
    }
}
