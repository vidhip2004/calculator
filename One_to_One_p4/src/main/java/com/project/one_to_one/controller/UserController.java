package com.project.one_to_one.controller;

import com.project.one_to_one.entity.User;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.project.one_to_one.repository.UserRepository;

import java.util.Optional;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        User savedUser=userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping("/{id}")
    public Optional<User> showUser(@PathVariable Integer id)
    {
        return userRepository.findById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user,@PathVariable Integer id)
    {
        user.setUserId(id);
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id)
    {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
