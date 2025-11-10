package umc.server.domain.test.exception;

import umc.server.global.apiPayload.code.BaseErrorCode;
import umc.server.global.apiPayload.exception.GeneralException;

public class TestException extends GeneralException {

    public TestException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
