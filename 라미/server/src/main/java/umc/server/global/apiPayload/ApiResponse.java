package umc.server.global.apiPayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import reactor.core.publisher.BaseSubscriber;
import umc.server.global.apiPayload.code.BaseErrorCode;
import umc.server.global.apiPayload.code.BaseSuccessCode;

@Getter // JSON 라이브러리(Jackson)가 응답 객체를 JSON으로 바꿀 때 내부적으로 Getter 호출
@AllArgsConstructor // final 필드는 선언 시 또는 생성자에서 반드시 초기화 해야 함
@JsonPropertyOrder({"isSuccess", "code", "message", "result"}) // JSON 직렬화 시 필드 순서 지정
public class ApiResponse<T> { // result에 임의 타입을 담기 위해 제네릭

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("result")
    private T result;

    // 성공한 경우
    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode code, T result) { // T는 보통 DTO 들어감
        return new ApiResponse<>(true, code.getCode(), code.getMessage(), result);
    }

    // 실패한 경우
    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result) {
        return new ApiResponse<>(false, code.getCode(), code.getMessage(), result);
    }
}
