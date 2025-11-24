package umc.server.global.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PageValidator.class)
public @interface ValidPage {

    String message() default "PAGE_MUST_BE_GREATER_THAN_ZERO";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
