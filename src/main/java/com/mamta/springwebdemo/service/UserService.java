package com.mamta.springwebdemo.service;

import com.mamta.springwebdemo.entity.User;
import com.mamta.springwebdemo.exception.UserNotFoundException;
import com.mamta.springwebdemo.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

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

    public User getUserById(Long id){
        if(id == null || id <0){
            throw new IllegalArgumentException("Invalid user Id provided");
        }
        Optional<User> byId =userRepo.findById(id);
        User user = byId.orElseThrow(() -> new  UserNotFoundException("User not found with id:" + id));
        return user;
    }


}
