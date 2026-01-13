package eu.lordbucket.libraryapp.controller;

import eu.lordbucket.libraryapp.service.BookService;
import eu.lordbucket.libraryapp.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ExportService exportService;

    // Requirement: Library Overview (Stats)
    // Visualizes the "Total books" and "Reading status breakdown"
    @GetMapping("/overview")
    public ResponseEntity<Map<String, Object>> getLibraryOverview() {
        Map<String, Object> stats = bookService.getLibraryOverview();
        return ResponseEntity.ok(stats);
    }

    // Requirement: Export Data (CSV)
    // Downloads the library as a file
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportLibrary() {
        String csvContent = exportService.exportBooksToCsv();
        byte[] csvBytes = csvContent.getBytes();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=my-library.csv")
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(csvBytes.length)
                .body(csvBytes);
    }
}