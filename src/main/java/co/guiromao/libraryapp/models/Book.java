package co.guiromao.libraryapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "UUID", strategy = "uuid4")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(name = "isbn", updatable = false, nullable = false, columnDefinition = "CHAR(36)")
    private UUID isbn;

    @Column(name = "book_title")
    private String title;

    @Column(name = "book_authors")
    private String authors;

    @Column(name = "is_lent")
    private boolean isLent;

    /*//Current member that has this book
    @Column(name = "current_member")
    private Member currentMember;*/

    @Column(name = "member_lent")
    private Long memberLentTo;

    //Will save to whom and when this book was lent
    @ElementCollection(targetClass = LendObject.class)
    @JsonIgnore
    private List<LendObject> lentList;

}
