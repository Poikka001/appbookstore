package mate.academy.intro.dto.fieldMatch;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        try {
            final Object firstObj = org.springframework.beans.BeanUtils.getPropertyDescriptor(value.getClass(), firstFieldName)
                    .getReadMethod().invoke(value);
            final Object secondObj = org.springframework.beans.BeanUtils.getPropertyDescriptor(value.getClass(), secondFieldName)
                    .getReadMethod().invoke(value);

            return firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        } catch (final Exception ignore) {
        }
        return true;
    }
}

