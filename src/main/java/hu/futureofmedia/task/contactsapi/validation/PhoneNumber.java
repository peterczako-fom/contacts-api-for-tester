package hu.futureofmedia.task.contactsapi.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PhoneValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {

    String message() default "badPhoneFormat";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
