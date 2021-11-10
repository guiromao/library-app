package co.guiromao.libraryapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * This class will keep track of when the book lending occurs, and to who
 */

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LendObject {

    private Member member;
    private Date initialDate;
    private Date finalDate;

}
