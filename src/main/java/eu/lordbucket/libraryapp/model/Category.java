package eu.lordbucket.libraryapp.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Rule: Each category can have many books
    @OneToMany(mappedBy = "category")
    private Set<Book> books = new HashSet<>();
}