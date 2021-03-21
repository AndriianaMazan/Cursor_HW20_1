package com.example.library.services;

import com.example.library.exceptions.UserNotFoundException;
import com.example.library.models.User;
import com.example.library.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findUserById(Integer id) throws UserNotFoundException {
        User user = userRepository.findUserById(id);

        if (user == null)
            throw new UserNotFoundException("User is not found");

        return user;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User save(User user){
        return userRepository.save(user);
    }
}
