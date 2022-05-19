package hu.futureofmedia.task.contactsapi.contacts.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailBannedDomainValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailBannedDomain {

    String message() default "bannedDomain";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
