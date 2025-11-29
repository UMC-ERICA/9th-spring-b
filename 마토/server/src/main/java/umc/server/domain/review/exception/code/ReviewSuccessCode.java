package umc.server.domain.review.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.FOUND,
            "REVIEW200_1",
            "해당 리뷰를 찾았습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
