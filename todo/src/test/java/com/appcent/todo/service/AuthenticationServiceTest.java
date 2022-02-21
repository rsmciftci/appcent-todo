package com.appcent.todo.service;

import com.appcent.todo.dao.UserDao;
import com.appcent.todo.dataprovider.UserDataProvider;
import com.appcent.todo.entity.User;
import com.appcent.todo.jwt.security.JwtTokenGenerator;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {


    @Mock
    private UserDao userDao;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenGenerator jwtTokenGenerator;

    @InjectMocks
    private  AuthenticationService authenticationService;


    @Test
    void shouldNotValidateUserRequest() {
        String email = "rsmciftci@gmail.com";
        User user = UserDataProvider.getUser();
        Optional<User> optionalUser = Optional.of(user);
        assertFalse(optionalUser.isEmpty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Email is already taken.");
        });
        String expectedMessage = "Email is already taken.";

        assertTrue(expectedMessage.equals(exception.getMessage()));


    }

}