package co.guiromao.libraryapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

/**
 * This class will keep track of when the book lending occurs, and to who
 */

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LendObject implements Serializable {

    private Long memberId;
    private Date lendDate;
    private Date returnDate;

}
