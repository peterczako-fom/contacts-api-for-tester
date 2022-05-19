package hu.futureofmedia.task.contactsapi.contacts.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContactListDto {

    private List<ContactListItemDto> content;
    private int page;
    private int pageSize;
    private long totalElementNumber;

    public ContactListDto(Page<ContactListItemDto> page) {
        this.content = page.getContent();
        this.page = page.getNumber();
        this.pageSize = page.getPageable().getPageSize();
        this.totalElementNumber = page.getTotalElements();
    }
}
