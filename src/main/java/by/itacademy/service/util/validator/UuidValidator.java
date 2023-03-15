package by.itacademy.service.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class UuidValidator implements ConstraintValidator<Uuid, UUID> {

    private static final String UUID_REGEX =
            "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-4[a-fA-F0-9]{3}-[89abAB][a-fA-F0-9]{3}-[a-fA-F0-9]{12}";

    @Override
    public boolean isValid(UUID uuid, ConstraintValidatorContext context) {
        if (uuid == null) {
            return false;
        }
        return uuid.toString().matches(UUID_REGEX);
    }
}