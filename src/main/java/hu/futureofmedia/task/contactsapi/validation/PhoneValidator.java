package hu.futureofmedia.task.contactsapi.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null || value.isEmpty()) {
            return true;
        }
        return value.matches("^\\+36\\d{9}$");
    }

}
