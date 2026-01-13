package eu.lordbucket.libraryapp.service;

import eu.lordbucket.libraryapp.model.Book;
import eu.lordbucket.libraryapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExportService {

    @Autowired
    private BookRepository bookRepository;

    // Ability to export the library data in formats like CSV
    public String exportBooksToCsv() {
        List<Book> books = bookRepository.findAll();
        StringBuilder csvContent = new StringBuilder();
        csvContent.append("ID,Title,ISBN,Status,Publisher\n");

        for (Book book : books) {
            csvContent.append(book.getId()).append(",")
                    .append(escapeSpecialCharacters(book.getTitle())).append(",")
                    .append(book.getIsbn()).append(",")
                    .append(book.getReadingStatus()).append(",")
                    .append(book.getPublisher().getName()).append("\n");
        }
        return csvContent.toString();
    }

    private String escapeSpecialCharacters(String data) {
        if (data == null) return "";
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
}