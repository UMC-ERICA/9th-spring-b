package umc.server.domain.member.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum FoodSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.OK, "FOOD200_1", "성공적으로 음식을 조회했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
