package com.mamta.springwebdemo.controller;

import com.mamta.springwebdemo.entity.User;
import com.mamta.springwebdemo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String userIndex(Model model){
        model.addAttribute("user",new User());
        return "user/index";
    }

    @PostMapping("/user/add")
    public String createUser(@ModelAttribute("user") User user,Model model){
        model.addAttribute("user", user);
        userService.createUser(user);
        model.addAttribute("message","User created successfully");
        model.addAttribute("users",userService.getAllUser());
        return "user/add";
    }

}
