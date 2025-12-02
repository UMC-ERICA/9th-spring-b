package umc.server.domain.member.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {
    FOUND(HttpStatus.OK, "MEMBER200_1", "성공적으로 사용자를 조회했습니다."),
    LOGOUT_SUCCESS(HttpStatus.OK, "MEMBER200_2", "성공적으로 로그아웃했습니다."),

    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
