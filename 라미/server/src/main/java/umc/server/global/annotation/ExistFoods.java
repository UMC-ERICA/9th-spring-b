package umc.server.global.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.server.global.validator.FoodExistValidator;

import java.lang.annotation.*;

@Documented // 사용자 정의 어노테이션 생성시
@Constraint(validatedBy = FoodExistValidator.class) // FoodExistValidator 클래스로 @ExistFoods가 붙은 대상 검증
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER}) // 어노테이션 적용 범위 지정 - 필드, 메서드, 파라미터에 모두 붙일 수 있음
@Retention(RetentionPolicy.RUNTIME) // 어노테이션 생명 주기 지정 - 실행하는 동안에만 유효
public @interface ExistFoods {
    String message() default "해당 음식이 존재하지 않습니다."; // 검증 실패 시 반환할 기본 에러 메시지
    Class<?>[] groups() default {}; // 검증 그룹 지정
    Class<? extends Payload>[] payload() default {}; // 검증 시 메타데이터 부여
}
