package umc.server.domain.test.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum TestErrorCode implements BaseErrorCode {

    // Test
    TEST_EXCEPTION(HttpStatus.BAD_REQUEST, "TEST400_1", "테스트"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
