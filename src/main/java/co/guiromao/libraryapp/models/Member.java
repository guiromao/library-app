package co.guiromao.libraryapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "members")
//@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name")
    private String name;

    @Column(name = "active")
    private boolean isActive;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Book> booksGot = new HashSet<>();

    @ManyToMany(mappedBy = "members", fetch = FetchType.EAGER)
    private Set<Campaign> campaigns = new HashSet<>();

    @ElementCollection
    //@CollectionTable(name = "active_periods")
    @JsonIgnore
    private Map<Date, Boolean> activePeriods;

    public void addBookToCurrent(Book book) {
        booksGot.add(book);
    }

    public void removeBookFromCurrent(Book book) {
        booksGot.remove(book);
    }

    public void toggleActive() {
        isActive = !isActive;
    }

}
