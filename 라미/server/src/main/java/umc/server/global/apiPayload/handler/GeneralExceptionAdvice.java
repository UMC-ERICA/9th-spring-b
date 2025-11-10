package umc.server.global.apiPayload.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.BaseErrorCode;
import umc.server.global.apiPayload.code.GeneralErrorCode;
import umc.server.global.apiPayload.exception.GeneralException;

@RestControllerAdvice // 전역 예외 처리
public class GeneralExceptionAdvice {

    // 프로젝트 발생하는 커스텀 예외 처리
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Void>> handleException(GeneralException ex) {
        return ResponseEntity.status(ex.getCode().getStatus())
                .body(ApiResponse.onFailure(ex.getCode(), null)
                );
    }

    // 그 외의 정의되지 않은 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception ex) {
        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code, ex.getMessage())
                );
    }
}
