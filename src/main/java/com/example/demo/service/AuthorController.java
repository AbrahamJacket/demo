package com.example.demo.service;

import com.example.demo.domain.Author;
import com.example.demo.domain.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @PostMapping("/author")
    @ResponseStatus(HttpStatus.CREATED)
    public Author saveBook(@RequestBody Author author) {
        return authorRepository.save(author);
    }

    @GetMapping("/author")
    @ResponseStatus(HttpStatus.OK)
    public List<Author> getAllBook() {
        return authorRepository.findAll();
    }

    @GetMapping("/author/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Author getBookById(@PathVariable Integer id) {

        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id = " + id));

        return author;
    }

    @PutMapping("/author/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Author refreshBook(@PathVariable("id") Integer id, @RequestBody Author author) {

        return authorRepository.findById(id)
                .map(entity -> {
                    entity.setFirstName(author.getFirstName());
                    entity.setLastName(author.getLastName());
                    entity.setBirthDate(author.getBirthDate());
                    return authorRepository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id = " + id));
    }

    @DeleteMapping("/author/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeBookById(@PathVariable Integer id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id = " + id));
        authorRepository.delete(author);
    }

    @DeleteMapping("/author")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllBooks() {
        authorRepository.deleteAll();
    }
}
