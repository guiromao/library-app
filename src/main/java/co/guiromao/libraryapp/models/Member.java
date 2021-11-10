package co.guiromao.libraryapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "members")
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

    @ManyToMany(mappedBy = "members")
    private Set<Campaign> campaigns = new HashSet<>();

}
