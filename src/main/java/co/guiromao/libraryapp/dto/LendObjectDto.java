package co.guiromao.libraryapp.dto;

import co.guiromao.libraryapp.models.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LendObjectDto {

    private Member member;
    private String initialDate;
    private String endDate;

}
