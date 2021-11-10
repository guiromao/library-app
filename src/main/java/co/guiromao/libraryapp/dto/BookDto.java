package co.guiromao.libraryapp.dto;

import co.guiromao.libraryapp.models.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private UUID isbn;
    private String title;
    private String authors;
    private Member currentMember;
    private boolean isLent;
    private List<LendObjectDto> lendObjects;

}
