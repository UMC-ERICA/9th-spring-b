package umc.server.domain.test.exception;

import umc.server.global.apiPayload.code.BaseErrorCode;
import umc.server.global.apiPayload.exception.GeneralException;

public class TestException extends GeneralException {
    public TestException(BaseErrorCode code) {
        super(code); // 부모 클래스 생성자 호출 - code 전달
    }
}
