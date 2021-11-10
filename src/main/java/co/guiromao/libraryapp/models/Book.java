package co.guiromao.libraryapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "isbn", updatable = false, nullable = false)
    private UUID isbn;

    @Column(name = "book_title")
    private String title;

    @Column(name = "book_authors")
    private String authors;

    @Column(name = "is_lent")
    private boolean isLent;

    //Current member that has this book
    @ManyToOne
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "isbn"))
    private Member currentMember;

    //Will save to whom and when this book was lent
    @ElementCollection(targetClass = LendObject.class)
    private List<LendObject> lentList;

    public void switchStatus() {
        isLent = !isLent;
    }

}
