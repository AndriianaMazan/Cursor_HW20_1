package com.example.library.repositories;

import com.example.library.models.Author;
import com.example.library.models.Book;
import com.example.library.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findBookById(Integer id);
    List<Book> findBooksByUser(User user);
    List<Book> findBooksByAuthorListContains(Author author);
    List<Book> findAllByUserIsNull();
    List<Book> findAllByUserIsNotNull();

}
