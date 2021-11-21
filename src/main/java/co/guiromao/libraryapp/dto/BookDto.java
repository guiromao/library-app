package co.guiromao.libraryapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private UUID isbn;
    private String title;
    private String authors;
    private boolean isLent;
    private Long memberLentTo;

}
