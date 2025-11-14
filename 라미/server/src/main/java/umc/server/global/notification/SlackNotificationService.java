package umc.server.global.notification;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j // Logger 객체 자동 생성
public class SlackNotificationService {

    @Value("${slack.webhook.url}") // application.yml에 저장된 slack.webhook.url값 변수에 주입
    private String slackWebhookUrl;

    private final RestTemplate restTemplate = new RestTemplate(); // 스프링 제공 HTTP 통신 도구

    @Async // 비동기 실행(응답 속도에 영향 없음)
    public void sendErrorNotification(Exception ex, HttpServletRequest request) {
        try {
            // 에러 메시지 포맷 생성
            String errorMessage = createErrorMessage(ex, request);

            // Slack 메시지 형식
            Map<String, Object> slackMessage = new HashMap<>();
            slackMessage.put("text", errorMessage);

            // HTTP 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> entity =
                    new HttpEntity<>(slackMessage, headers);

            // Slack에 POST 요청
            restTemplate.postForEntity(slackWebhookUrl, entity, String.class);

        } catch (Exception e) {
            // Slack 전송 실패해도 원래 에러 처리는 계속 진행
            log.error("Failed to send Slack notification: {}", e.getMessage());
        }
    }

    private String createErrorMessage(Exception ex, HttpServletRequest request) {
        return String.format(
                "🚨 *500 Internal Server Error 발생!*\n\n" +
                        "*에러 타입:* `%s`\n" +
                        "*에러 메시지:* %s\n" +
                        "*발생 시각:* %s\n" +
                        "*요청 Method:* %s\n" +
                        "*요청 URL:* %s\n" +
                        "*Query String:* %s",
                ex.getClass().getSimpleName(),
                ex.getMessage(),
                LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                ),
                request.getMethod(),
                request.getRequestURI(),
                request.getQueryString() != null ? request.getQueryString() : "없음"
        );
    }
}
