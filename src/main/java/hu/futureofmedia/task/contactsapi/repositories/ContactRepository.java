package hu.futureofmedia.task.contactsapi.repositories;

import hu.futureofmedia.task.contactsapi.dto.ContactListItemDto;
import hu.futureofmedia.task.contactsapi.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query("select new hu.futureofmedia.task.contactsapi.dto.ContactListItemDto(" +
            "c.id, concat(c.firstName, ' ', c.lastName), c.email, c.phoneNumber, c.company.name) from Contact c " +
            "where c.status = :status")
    Page<ContactListItemDto> getAllActiveContact(Pageable pageable, Contact.Status status);

    default Page<ContactListItemDto> getAllActiveContact(Pageable pageable) {
        return getAllActiveContact(pageable, Contact.Status.ACTIVE);
    }
}
