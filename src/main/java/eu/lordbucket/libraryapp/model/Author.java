package eu.lordbucket.libraryapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Rule: Each author can write many books (at least one)
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

    // Rule: Each author can have many books' series
    // Rule: Each series can be written by many authors
    @ManyToMany
    @JoinTable(
            name = "author_series",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "series_id")
    )
    @JsonIgnore
    private Set<Series> series = new HashSet<>();
}