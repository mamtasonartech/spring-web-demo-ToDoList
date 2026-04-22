package com.mamta.springwebdemo.controller;

import com.mamta.springwebdemo.entity.Error;
import com.mamta.springwebdemo.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Error> handleUserNotFound(Exception e){
        return ResponseEntity.status(404).body(Error.builder().message("User Not Found").code("User Not Found").build());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Error> handleBadRequest(Exception e){
        return ResponseEntity.status(400).body(Error.builder().message("User Not Found").code("User Not Found").build());
    }

}
