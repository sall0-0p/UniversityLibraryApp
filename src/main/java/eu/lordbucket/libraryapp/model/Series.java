package eu.lordbucket.libraryapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Rule: Each series can have many books (at least one)
    @OneToMany(mappedBy = "series")
    private Set<Book> books = new HashSet<>();

    // Inverse side of Author <-> Series relationship
    @ManyToMany(mappedBy = "series")
    @JsonIgnore
    private Set<Author> authors = new HashSet<>();
}