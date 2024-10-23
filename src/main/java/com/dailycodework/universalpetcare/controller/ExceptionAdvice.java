package com.dailycodework.universalpetcare.controller;

import com.dailycodework.universalpetcare.exception.UserAlreadyExistsException;
import com.dailycodework.universalpetcare.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> userAlreadyExistsException(@Autowired UserAlreadyExistsException exception) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> resourceNotFoundException(@Autowired ResourceNotFoundException exception) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
