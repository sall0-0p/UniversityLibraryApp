package eu.lordbucket.libraryapp.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String authorName; // For display or simple listing, though we have a relationship below

    private String isbn;
    private Integer publicationYear;
    private Integer numberOfPages;

    @Enumerated(EnumType.STRING)
    private ReadingStatus readingStatus;

    @Lob
    private String notes;

    // Rule: Each book is published by exactly one publisher
    @ManyToOne(optional = false)
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    // Rule: Each book belongs to at most one series
    @ManyToOne(optional = true)
    @JoinColumn(name = "series_id")
    private Series series;

    // Rule: Each book can belong to one categor
    @ManyToOne(optional = true)
    @JoinColumn(name = "category_id")
    private Category category;

    // Rule: Each book can have many authors (but doesn't have to)
    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

    // Rule: Each book can represent many genres
    @ManyToMany
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    // Rule: Each book can have many topics
    @ManyToMany
    @JoinTable(
            name = "book_topic",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private Set<Topic> topics = new HashSet<>();
}

