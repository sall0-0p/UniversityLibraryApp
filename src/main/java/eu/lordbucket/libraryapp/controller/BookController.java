package eu.lordbucket.libraryapp.controller;

import eu.lordbucket.libraryapp.model.Book;
import eu.lordbucket.libraryapp.model.ReadingStatus;
import eu.lordbucket.libraryapp.repository.BookRepository;
import eu.lordbucket.libraryapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*") // Allow your minimalist frontend to hit this easily
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    // 1. GET ALL or SEARCH (Paged)
    // Usage: GET /api/books?title=Harry&page=0&size=10
    @GetMapping
    public ResponseEntity<Page<Book>> getAllBooks(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(bookService.searchBooks(title, pageable));
    }

    // 1.1 GET ALL (Unpaged - specifically for dashboard recent list if needed, strictly speaking we can reuse paged)
    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooksUnpaged() {
        return ResponseEntity.ok(bookService.getAllBooksUnpaged());
    }

    // 2. GET SINGLE BOOK
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. CREATE BOOK
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }

    // 4. UPDATE BOOK (Details)
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        try {
            return ResponseEntity.ok(bookService.updateBook(id, bookDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. DELETE BOOK
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }

    // 6. UPDATE READING STATUS (Specialized Endpoint)
    // Usage: PATCH /api/books/1/status?status=READING
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestParam ReadingStatus status) {
        bookService.updateReadingStatus(id, status);
        return ResponseEntity.ok().build();
    }
}