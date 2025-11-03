package umc.server.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class ReviewRequestDTO {

    @Getter
    public static class CreateDTO {

        @NotNull(message = "사용자 ID는 필수입니다.")
        private Long memberId;

        @NotNull(message = "가게 ID는 필수입니다.")
        private Long storeId;

        @NotBlank(message = "리뷰 제목은 비워둘 수 없습니다.")
        private String title;

        @NotBlank(message = "리뷰 내용은 비워둘 수 없습니다.")
        private String content;

        @NotNull(message = "평점은 필수입니다.")
        @Min(value = 0, message = "평점은 0점 이상이어야 합니다.")
        @Max(value = 5, message = "평점은 5점 이하이어야 합니다.")
        private Float rating;
    }
}
