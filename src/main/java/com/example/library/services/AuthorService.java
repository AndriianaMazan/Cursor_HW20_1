package com.example.library.services;

import com.example.library.exceptions.AuthorNotFoundException;
import com.example.library.models.Author;
import com.example.library.repositories.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    public Author findAuthorById(Integer id) throws AuthorNotFoundException {
        Author author = authorRepository.findAuthorById(id);

        if (author == null)
            throw new AuthorNotFoundException("Author is not found.");

        return author;
    }

    public Author save(Author author){
        return authorRepository.save(author);
    }
}
