package umc.server.global.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.server.domain.member.enums.FoodCategory;
import umc.server.domain.member.exception.code.FoodErrorCode;
import umc.server.domain.member.repository.FoodRepository;
import umc.server.global.annotation.ExistFoods;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FoodExistValidator implements ConstraintValidator<ExistFoods, List<FoodCategory>> {
    // 초기화 메서드는 디폴트 구현있음

    private final FoodRepository foodRepository;

    @Override
    public boolean isValid(List<FoodCategory> values, ConstraintValidatorContext context) {
        if (values == null || values.isEmpty()) {
            return true; // null이나 빈 리스트 허용
        }

        // 모든 FoodCategory에 해당하는 Food가 DB에 존재하는지 확인
        boolean isValid = values.stream()
                .allMatch(category -> foodRepository.findByCategory(category).isPresent());

        if (!isValid) {
            // 기본 메시지 비활성화
            context.disableDefaultConstraintViolation();
            // 커스텀 메시지 설정
            context.buildConstraintViolationWithTemplate(FoodErrorCode.NOT_FOUND.getMessage())
                    .addConstraintViolation();
        }
        return isValid;
    }

}
