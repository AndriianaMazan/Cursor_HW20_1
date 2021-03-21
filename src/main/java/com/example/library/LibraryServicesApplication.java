package com.example.library;

import com.example.library.models.Author;
import com.example.library.models.Book;
import com.example.library.models.User;
import com.example.library.services.AuthorService;
import com.example.library.services.BookService;
import com.example.library.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class LibraryServicesApplication {
    private final AuthorService authorService;
    private final BookService bookService;
    private final UserService userService;

    public LibraryServicesApplication(AuthorService authorService,
                                      BookService bookService,
                                      UserService userService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(LibraryServicesApplication.class, args);
    }

    @PostConstruct
    public void addUsers(){
        User user1 = new User();
        user1.setName("John");
        User user2 = new User();
        user2.setName("Evan");

        Author author1 = new Author();
        author1.setName("Agata Kristi");
        Author author2 = new Author();
        author2.setName("Dan Brown");

        Book book1 = new Book();
        book1.setTitle("Murder on the Orient Express");
        Book book2 = new Book();
        book2.setTitle("Da Vinci Code");

        book1.setAuthorList(List.of(author1));
        book2.setAuthorList(List.of(author2));

        author1.setBookList(List.of(book1));
        author2.setBookList(List.of(book2));

        book1.setUser(user1);
        book2.setUser(user2);

        user1.setBookList(List.of(book1));
        user2.setBookList(List.of(book2));

        authorService.save(author1);
        authorService.save(author2);

        bookService.save(book1);
        bookService.save(book2);

        userService.save(user1);
        userService.save(user2);
    }
}
