package umc.server.domain.member.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "해당 사용자를 찾지 못했습니다."),
    SESSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_2", "세션이 존재하지 않습니다"),
    INVALID(HttpStatus.BAD_REQUEST, "MEMBER400_1", "비밀번호가 일치하지 않습니다."),

    // token
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "MEMBER4001", "리프레시 토큰을 찾을 수 없습니다."),
    REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "MEMBER4002", "리프레시 토큰이 만료되었습니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "MEMBER4003", "유효하지 않은 리프레시 토큰입니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
