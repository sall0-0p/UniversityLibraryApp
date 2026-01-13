package eu.lordbucket.libraryapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Rule: Each publisher can publish many books (at least one)
    @JsonIgnore
    @OneToMany(mappedBy = "publisher")
    private Set<Book> books = new HashSet<>();
}