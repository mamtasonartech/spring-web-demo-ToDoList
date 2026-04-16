package com.mamta.springwebdemo.service;

import com.mamta.springwebdemo.entity.User;
import com.mamta.springwebdemo.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User createUser(User user){
        user.setCreatedAt(Instant.now());
        return userRepo.save(user);
    }

    public List<User> getAllUser(){
        return userRepo.findAll();
    }


}
