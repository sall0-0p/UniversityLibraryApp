package eu.lordbucket.libraryapp.service;

import eu.lordbucket.libraryapp.model.Book;
import eu.lordbucket.libraryapp.model.ReadingStatus;
import eu.lordbucket.libraryapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Add new books [cite: 10]
    @Transactional
    public Book addBook(Book book) {
        // Here you might add validation logic before saving
        return bookRepository.save(book);
    }

    // Edit or remove book entries [cite: 11]
    @Transactional
    public Book updateBook(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setTitle(bookDetails.getTitle());
        book.setIsbn(bookDetails.getIsbn());
        book.setNotes(bookDetails.getNotes()); // Update notes [cite: 17]
        book.setReadingStatus(bookDetails.getReadingStatus());

        // Update relationships as needed...
        return bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    // Track reading progress [cite: 15]
    @Transactional
    public void updateReadingStatus(Long bookId, ReadingStatus status) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setReadingStatus(status);
        bookRepository.save(book);
    }

    // Search functionality
    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    // Library Overview: Display total number of books and reading status breakdown [cite: 23]
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