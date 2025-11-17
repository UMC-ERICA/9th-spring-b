package umc.server.domain.user.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum UserSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.OK,
            "MEMBER200_1",
            "성공적으로 사용자를 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
