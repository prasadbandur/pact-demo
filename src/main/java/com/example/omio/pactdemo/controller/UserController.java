package com.example.omio.pactdemo.controller;

import com.example.omio.pactdemo.models.IdObject;
import com.example.omio.pactdemo.models.User;
import com.example.omio.pactdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/user-service/users")
    public ResponseEntity<IdObject> createUser(@RequestBody @Valid User user) {
        @Valid User savedUser = userRepository.save(user);
        return ResponseEntity.status(201).body(new IdObject(savedUser.getId()));
    }
}
