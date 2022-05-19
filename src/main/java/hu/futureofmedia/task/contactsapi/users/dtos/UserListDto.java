package hu.futureofmedia.task.contactsapi.users.dtos;

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
public class UserListDto {

    private List<UserListItemDto> content;
    private int page;
    private int pageSize;
    private long totalElementNumber;

    public UserListDto(Page<UserListItemDto> page) {
        this.content = page.getContent();
        this.page = page.getNumber();
        this.pageSize = page.getPageable().getPageSize();
        this.totalElementNumber = page.getTotalElements();
    }
}
