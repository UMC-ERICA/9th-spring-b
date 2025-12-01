package umc.server.domain.user.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "User404_1",
            "해당 사용자를 찾지 못했습니다."),
    INVALID(HttpStatus.UNAUTHORIZED,
            "User401_2",
            "이메일 또는 비밀번호가 올바르지 않습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
