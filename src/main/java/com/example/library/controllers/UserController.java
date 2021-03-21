package com.example.library.controllers;

import com.example.library.exceptions.UserNotFoundException;
import com.example.library.models.User;
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
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/users",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/users/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getById(
            @PathVariable(value = "userId") Integer userId
    ) {
        try {
            return new ResponseEntity<>(userService.findUserById(userId),
                    HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
