package eu.lordbucket.libraryapp.repository;

import eu.lordbucket.libraryapp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByPublicationYear(Integer year);

    List<Book> findByGenres_NameIgnoreCase(String genreName);

    List<Book> findByAuthors_NameIgnoreCase(String authorName);

    // For Library Overview
    @Query("SELECT b.readingStatus, COUNT(b) FROM Book b GROUP BY b.readingStatus")
    List<Object[]> countBooksByReadingStatus();
}