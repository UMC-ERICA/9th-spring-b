package umc.server.domain.test.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum TestErrorCode implements BaseErrorCode {

    // For test
    TEST_EXCEPTION(HttpStatus.BAD_REQUEST, "TEST400_1", "이거는 테스트"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
