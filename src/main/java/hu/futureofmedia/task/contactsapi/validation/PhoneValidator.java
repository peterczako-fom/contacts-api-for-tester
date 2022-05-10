package hu.futureofmedia.task.contactsapi.validation;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if(!StringUtils.hasLength(phoneNumber)) {
            return true;
        }
        return phoneNumber.matches("^\\+36\\d{9}$");
    }

}
