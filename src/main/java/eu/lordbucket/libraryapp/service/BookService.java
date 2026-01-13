package eu.lordbucket.libraryapp.service;

import eu.lordbucket.libraryapp.model.Book;
import eu.lordbucket.libraryapp.model.ReadingStatus;
import eu.lordbucket.libraryapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public Book updateBook(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Basic Fields
        book.setTitle(bookDetails.getTitle());
        book.setAuthorName(bookDetails.getAuthorName());
        book.setIsbn(bookDetails.getIsbn());
        book.setPublicationYear(bookDetails.getPublicationYear());
        book.setNumberOfPages(bookDetails.getNumberOfPages());
        book.setNotes(bookDetails.getNotes());
        book.setReadingStatus(bookDetails.getReadingStatus());

        // Relationships (Update Foreign Keys & Join Tables)
        book.setPublisher(bookDetails.getPublisher());
        book.setCategory(bookDetails.getCategory());
        book.setSeries(bookDetails.getSeries());

        // Collections (Sets) - clear and add ensures Hibernate manages the collection correctly
        if (book.getGenres() != null) {
            book.getGenres().clear();
            if (bookDetails.getGenres() != null) {
                book.getGenres().addAll(bookDetails.getGenres());
            }
        } else {
            book.setGenres(bookDetails.getGenres());
        }

        if (book.getTopics() != null) {
            book.getTopics().clear();
            if (bookDetails.getTopics() != null) {
                book.getTopics().addAll(bookDetails.getTopics());
            }
        } else {
            book.setTopics(bookDetails.getTopics());
        }

        return bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void updateReadingStatus(Long bookId, ReadingStatus status) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setReadingStatus(status);
        bookRepository.save(book);
    }

    // Paged Search
    public Page<Book> searchBooks(String title, Pageable pageable) {
        if (title != null && !title.isEmpty()) {
            return bookRepository.findByTitleContainingIgnoreCase(title, pageable);
        }
        return bookRepository.findAll(pageable);
    }

    // Unpaged (for simple lists)
    public List<Book> getAllBooksUnpaged() {
        return bookRepository.findAll();
    }

    public Map<String, Object> getLibraryOverview() {
        long totalBooks = bookRepository.count();
        List<Object[]> statusCounts = bookRepository.countBooksByReadingStatus();

        Map<String, Long> statusMap = statusCounts.stream()
                .collect(Collectors.toMap(
                        obj -> ((ReadingStatus) obj[0]).name(),
                        obj -> (Long) obj[1]
                ));

        return Map.of(
                "totalBooks", totalBooks,
                "statusBreakdown", statusMap
        );
    }
}