package com.appcent.todo.service;

import com.appcent.todo.converter.UserMapper;
import com.appcent.todo.dao.UserDao;
import com.appcent.todo.dto.UserRequestDto;
import com.appcent.todo.dto.UserSavingDto;
import com.appcent.todo.entity.User;
import com.appcent.todo.jwt.security.EnumJwtConstant;
import com.appcent.todo.jwt.security.JwtTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;

    public String login(UserRequestDto userRequestDto){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken
                (userRequestDto.getUsername(),userRequestDto.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenGenerator.generateJwtToken(authentication);

        return EnumJwtConstant.BEARER.getConstant() + token;

    }

    public User register(UserSavingDto userSavingDto){

        String password = userSavingDto.getPassword();
        User user = UserMapper.INSTANCE.convertUserSavingDtoToUser(userSavingDto);
        password = passwordEncoder.encode(password);
        user.setPassword(password);
        user = userDao.save(user);
        return user;
    }

    public void validateUserRequest(String email) {
        Optional<User> optionalUser = userDao.findByEmail(email);
        if(optionalUser.isPresent()){
            throw new RuntimeException("Email is already taken.");
        }
    }
}
