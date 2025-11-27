package umc.server.domain.member.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum MemberMissionErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER_MISSION404_1", "해당 회원의 미션 정보가 존재하지 않습니다."),
    ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "MEMBER_MISSION400_2", "이미 완료된 미션입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
