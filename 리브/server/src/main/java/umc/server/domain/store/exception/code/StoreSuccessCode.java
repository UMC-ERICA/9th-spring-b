package umc.server.domain.store.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.OK, "STORE200_1", "성공적으로 가게를 조회했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
