package by.itacademy.service.util.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author Max Strybuk
 * @date 2023/2/25
 */


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = UuidValidator.class)
@Documented
public @interface Uuid {

    String message() default "invalid uuid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}