package com.example.assignment.controller;
import com.example.assignment.entity.User;
import com.example.assignment.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserManagementController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = "/create-user")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) throws Exception {
        log.info("Hit received to create new user");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping(value = "/fetch-users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("Hit received to fetch all users");
        try {
            List<User> users = userService.findAllUsers();
            return ResponseEntity.ok(users);
        }catch (Exception e){
            return new ResponseEntity("Something went wrong in fetching datar", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}