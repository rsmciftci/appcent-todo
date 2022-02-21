package com.appcent.todo.service;

import com.appcent.todo.converter.UserMapper;
import com.appcent.todo.dao.UserDao;
import com.appcent.todo.dto.UserSavingDto;
import com.appcent.todo.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    public UserSavingDto save(UserSavingDto userSavingDto){

        User user = UserMapper.INSTANCE.convertUserSavingDtoToUser(userSavingDto);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        user = userDao.save(user);

        UserSavingDto savedUserSavingDto = UserMapper.INSTANCE.convertUserToUserSavingDto(user);

        return savedUserSavingDto;
    }


    public Long findIdByUsername(String username) {

        Optional<User> optionalUser  = userDao.findByUsername(username);
        User user = null;

        if(optionalUser.isPresent()){
            user = optionalUser.get();
        }
        return user.getId();
    }


}
