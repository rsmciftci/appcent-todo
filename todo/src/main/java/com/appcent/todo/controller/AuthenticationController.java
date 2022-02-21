package com.appcent.todo.controller;


import com.appcent.todo.dto.UserRequestDto;
import com.appcent.todo.dto.UserSavingDto;
import com.appcent.todo.entity.User;

import com.appcent.todo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth/")
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("login")
    public String login(@RequestBody UserRequestDto userRequestDto){
        return authenticationService.login(userRequestDto);
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody @Valid UserSavingDto userSavingDto){

        String email = userSavingDto.getEmail();

        try {
            authenticationService.validateUserRequest(email);
        } catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        User user = authenticationService.register(userSavingDto);


        return new ResponseEntity<>("User registered.",HttpStatus.CREATED);


    }


}
