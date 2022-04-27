package hu.futureofmedia.task.contactsapi.validation;

import hu.futureofmedia.task.contactsapi.repositories.BannedDomainRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class EmailBannedDomainValidator implements ConstraintValidator<EmailBannedDomain, String> {

    private final BannedDomainRepository bannedDomainRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if(email == null || email.isEmpty()) {
            return true;
        }
        var at = email.indexOf('@');
        return bannedDomainRepository.findByName(email.substring(at + 1)).isEmpty();
    }
}
