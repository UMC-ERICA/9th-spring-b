package umc.server.domain.mission.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    MISSION_LIST_FOUND(HttpStatus.OK, "MISSION200_1", "미션 목록을 성공적으로 조회했습니다."),
    MISSION_FOUND(HttpStatus.OK, "MISSION200_2", "미션을 성공적으로 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
