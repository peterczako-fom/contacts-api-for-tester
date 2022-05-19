package hu.futureofmedia.task.contactsapi.contacts.repositories;

import hu.futureofmedia.task.contactsapi.contacts.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query("select c from Contact c where c.status = :status")
    Page<Contact> getAllContactByStatus(Pageable pageable, Contact.Status status);

    @Modifying
    @Query("update Contact c set c.status = :status where c.id = :id")
    void updateStatusById(Long id, Contact.Status status);

}
