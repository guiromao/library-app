package co.guiromao.libraryapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "members")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name")
    private String name;

    @Column(name = "active")
    private boolean isActive;

    @OneToMany(mappedBy = "currentMember", fetch = FetchType.EAGER, targetEntity = Book.class)
    private Set<Book> booksGot;

}
