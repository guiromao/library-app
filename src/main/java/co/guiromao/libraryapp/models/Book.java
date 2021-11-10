package co.guiromao.libraryapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

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

    //Current member that has this book
    @Column(name = "current_member")
    private Member currentMember;

    //Will save to whom and when this book was lent
    @ElementCollection(targetClass = LendObject.class)
    private List<LendObject> lentList;

}
