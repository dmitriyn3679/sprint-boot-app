package mate.academy.bookstore.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Objects;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        try {
            Field first = value.getClass().getDeclaredField(firstFieldName);
            Field second = value.getClass().getDeclaredField(secondFieldName);
            first.setAccessible(true);
            second.setAccessible(true);

            Object firstVal = first.get(value);
            Object secondVal = second.get(value);

            return Objects.equals(firstVal, secondVal);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return false;
        }
    }
}
