package com.example.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.entities.User;
import com.example.spring.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(String githubId, String name, String email) {
        return userRepository.save(new User(null, githubId, name, email));
    }
}
