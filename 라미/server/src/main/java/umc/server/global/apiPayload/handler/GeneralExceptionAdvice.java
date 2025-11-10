package umc.server.global.apiPayload.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.BaseErrorCode;
import umc.server.global.apiPayload.code.GeneralErrorCode;
import umc.server.global.apiPayload.exception.GeneralException;
import umc.server.global.notification.SlackNotificationService;

@RestControllerAdvice // 전역 예외 처리
@RequiredArgsConstructor // 생성자 주입
public class GeneralExceptionAdvice {

    private final SlackNotificationService slackNotificationService;

    // 프로젝트 발생하는 커스텀 예외 처리
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Void>> handleException(GeneralException ex) {
        return ResponseEntity.status(ex.getCode().getStatus())
                .body(ApiResponse.onFailure(ex.getCode(), null)
                );
    }

    // 불필요한 Slack 알림 방지 (favicon 알림)
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNoResourceFound(
            NoResourceFoundException ex
    ) {
        return ResponseEntity.notFound().build();
    }

    // 그 외의 정의되지 않은 모든 예외 처리 (500 에러 처리 + Slack 알림)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(
            Exception ex,
            HttpServletRequest request
    ) {

        // Slack 알림 전송 (비동기)
        slackNotificationService.sendErrorNotification(ex,request);

        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code, ex.getMessage())
                );
    }
}
