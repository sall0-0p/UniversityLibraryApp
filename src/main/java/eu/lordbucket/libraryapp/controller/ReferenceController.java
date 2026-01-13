package eu.lordbucket.libraryapp.controller;

import eu.lordbucket.libraryapp.model.*;
import eu.lordbucket.libraryapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/references")
@CrossOrigin(origins = "*")
public class ReferenceController {

    @Autowired private AuthorRepository authorRepository;
    @Autowired private PublisherRepository publisherRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private SeriesRepository seriesRepository;
    @Autowired private GenreRepository genreRepository;
    @Autowired private TopicRepository topicRepository;

    // --- Authors ---
    @GetMapping("/authors")
    public List<Author> getAllAuthors() { return authorRepository.findAll(); }

    @PostMapping("/authors")
    public Author createAuthor(@RequestBody Author author) { return authorRepository.save(author); }

    // --- Publishers ---
    @GetMapping("/publishers")
    public List<Publisher> getAllPublishers() { return publisherRepository.findAll(); }

    @PostMapping("/publishers")
    public Publisher createPublisher(@RequestBody Publisher publisher) { return publisherRepository.save(publisher); }

    // --- Categories ---
    @GetMapping("/categories")
    public List<Category> getAllCategories() { return categoryRepository.findAll(); }

    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category category) { return categoryRepository.save(category); }

    // --- Series ---
    @GetMapping("/series")
    public List<Series> getAllSeries() { return seriesRepository.findAll(); }

    @PostMapping("/series")
    public Series createSeries(@RequestBody Series series) { return seriesRepository.save(series); }

    // --- Genres ---
    @GetMapping("/genres")
    public List<Genre> getAllGenres() { return genreRepository.findAll(); }

    @PostMapping("/genres")
    public Genre createGenre(@RequestBody Genre genre) { return genreRepository.save(genre); }

    // --- Topics ---
    @GetMapping("/topics")
    public List<Topic> getAllTopics() { return topicRepository.findAll(); }

    @PostMapping("/topics")
    public Topic createTopic(@RequestBody Topic topic) { return topicRepository.save(topic); }
}