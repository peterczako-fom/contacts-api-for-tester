package hu.futureofmedia.task.contactsapi.contacts.validations;

import hu.futureofmedia.task.contactsapi.contacts.repositories.BannedDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class EmailBannedDomainValidator implements ConstraintValidator<EmailBannedDomain, String> {

    private final BannedDomainRepository bannedDomainRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if(!StringUtils.hasLength(email)) {
            return true;
        }
        var at = email.indexOf('@');
        return bannedDomainRepository.findByName(email.substring(at + 1)).isEmpty();
    }
}
