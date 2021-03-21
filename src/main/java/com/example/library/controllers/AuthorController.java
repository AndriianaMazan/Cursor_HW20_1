package com.example.library.controllers;

import com.example.library.exceptions.AuthorNotFoundException;
import com.example.library.models.Author;
import com.example.library.services.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping(value = "/authors",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Author>> getAll() {
        return new ResponseEntity<>(authorService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/authors/{authorId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Author> getById(
            @PathVariable(value = "authorId") Integer authorId
    ) {
        try {
            return new ResponseEntity<>(authorService.findAuthorById(authorId),
                    HttpStatus.OK);
        } catch (AuthorNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
