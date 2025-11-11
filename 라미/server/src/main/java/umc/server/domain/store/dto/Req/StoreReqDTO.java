package umc.server.domain.store.dto.Req;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.*;

public class StoreReqDTO {

    @Getter
    @Setter // GET 요청
    public static class SearchDTO {
        private String region;
        private String keyword;

        @Pattern(regexp = "latest|name") // 문자열 필드 값이 정규 표현식(regexp)과 일치
        private String sort = "latest";

        @Min(0)
        private int page = 0;

        @Min(1)
        private int size = 10;
    }

    @Getter
    @Setter
    public static class SearchCursorDTO {
        private String region;
        private String keyword;

        @Pattern(regexp = "latest|name")
        private String sort = "latest";

        private long cursor;

        @Min(1)
        private int size = 10;
    }
}
