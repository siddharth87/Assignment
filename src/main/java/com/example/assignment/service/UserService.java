package com.example.assignment.service;

import com.example.assignment.entity.User;
import com.example.assignment.respository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User saveUser(User user) throws Exception {
        try {
            log.info("Going to save user in database for username {}", user.getUsername());
            userRepository.save(user);
        }catch (Exception e){
            log.warn("Something went wrong while saving data in databse for username {}", user.getUsername(), e);
            throw new Exception("Something went wrong in saving data");
        }
        return user;
    }

    public List<User> findAllUsers() throws Exception {
        try {
            log.info("Going to fetch all users from database");
            return userRepository.findAll();
        }catch (Exception e){
            log.warn("Something went wrong while fetching data", e);
            throw new Exception("Something went wrong in fetching data");
        }
    }
}
