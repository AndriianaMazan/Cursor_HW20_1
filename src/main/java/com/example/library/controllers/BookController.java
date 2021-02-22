package com.example.library.controllers;

import com.example.library.exceptions.AuthorNotFoundException;
import com.example.library.exceptions.BookNotFoundException;
import com.example.library.exceptions.UserNotFoundException;
import com.example.library.models.Author;
import com.example.library.models.Book;
import com.example.library.models.User;
import com.example.library.services.AuthorService;
import com.example.library.services.BookService;
import com.example.library.services.UserService;
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
public class BookController {
    private final BookService bookService;
    private final UserService userService;
    private final AuthorService authorService;

    @GetMapping(value = "/books",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> getAll() {
        return new ResponseEntity<>(bookService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/books/not-taken",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> getAllNotTaken() {
        try {
            return new ResponseEntity<>(bookService.getAllNotTakenBooks(),
                    HttpStatus.OK);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/books/taken",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> getAllTaken() {
        try {
            return new ResponseEntity<>(bookService.getAllTakenBooks(),
                    HttpStatus.OK);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/books/{bookId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> getById(
            @PathVariable(value = "bookId") Integer bookId
    ) {
        try {
            return new ResponseEntity<>(bookService.findBookById(bookId),
                    HttpStatus.OK);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/books/user={userId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> getByUserId(
            @PathVariable(value = "userId") Integer userId
    ) {
        try {
            User user = userService.findUserById(userId);
            return new ResponseEntity<>(bookService.findBookByUser(user),
                    HttpStatus.OK);
        } catch (UserNotFoundException | BookNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/books/author={authorId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> getByAuthorId(
            @PathVariable(value = "authorId") Integer authorId
    ) {
        try {
            Author author = authorService.findAuthorById(authorId);
            return new ResponseEntity<>(bookService.findBookByAuthor(author),
                    HttpStatus.OK);
        } catch (BookNotFoundException | AuthorNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
