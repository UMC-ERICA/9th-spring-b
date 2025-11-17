package umc.server.domain.mission.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404", "미션을 찾을 수 없습니다."),
    NOT_STORE_MISSION(HttpStatus.BAD_REQUEST, "MISSION400", "해당 가게의 미션이 아닙니다."),
    ALREADY_CHALLENGING(HttpStatus.CONFLICT, "MISSION409", "이미 도전 중인 미션입니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
