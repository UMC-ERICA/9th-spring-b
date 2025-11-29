package umc.server.global.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import umc.server.global.annotation.Page;

public class PageValidator implements ConstraintValidator<Page, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // null이면 일단 통과 (required 여부는 @RequestParam에서 결정)
        if (value == null) return true;
        return value >= 1;
    }
}
