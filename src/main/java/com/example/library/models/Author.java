package com.example.library.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Component
@Entity
@Table(name = "authors")
@Getter
@Setter
public class Author implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Integer id;

    @Column
    private String name;

    @ManyToMany(
            cascade = {CascadeType.ALL})
    @JsonIgnore
    private List<Book> bookList;

    @Override
    public String toString() {
        return "Author name=" + name + " ";
    }
}
