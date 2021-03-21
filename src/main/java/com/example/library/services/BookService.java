package com.example.library.services;

import com.example.library.exceptions.BookNotFoundException;
import com.example.library.models.Author;
import com.example.library.models.Book;
import com.example.library.models.User;
import com.example.library.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book findBookById(Integer id) throws BookNotFoundException {
        Book book = bookRepository.findBookById(id);

        if (book == null)
            throw new BookNotFoundException("Book is not found!");

        return book;
    }

    public List<Book> findBookByUser(User user) throws BookNotFoundException {
        List<Book> books = bookRepository.findBooksByUser(user);

        if (books == null)
            throw new BookNotFoundException("Book is not found!");

        return books;
    }

    public List<Book> findBookByAuthor(Author author) throws BookNotFoundException {
        List<Book> books = bookRepository.findBooksByAuthorListContains(author);

        if (books == null)
            throw new BookNotFoundException("Book is not found!");

        return books;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public List<Book> getAllTakenBooks() throws BookNotFoundException {
        List<Book> books = bookRepository.findAllByUserIsNotNull();

        if (books == null)
            throw new BookNotFoundException("Books are not found!");

        return books;
    }

    public List<Book> getAllNotTakenBooks() throws BookNotFoundException {
        List<Book> books = bookRepository.findAllByUserIsNull();

        if (books == null)
            throw new BookNotFoundException("Books are not found!");

        return books;
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }
}
