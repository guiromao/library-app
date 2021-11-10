package co.guiromao.libraryapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Date;

/**
 * This class will keep track of when the book lending occurs, and to who
 */

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LendObject {

    private String memberId;
    private String memberName;
    private Date initialDate;
    private Date finalDate;

}
