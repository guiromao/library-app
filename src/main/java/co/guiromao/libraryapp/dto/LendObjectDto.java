package co.guiromao.libraryapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LendObjectDto implements Serializable {

    private Long memberId;
    private Date lendDate;
    private Date returnDate;

}
