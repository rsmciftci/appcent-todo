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

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserSavingDto save(UserSavingDto userSavingDto){

        User user = UserMapper.INSTANCE.convertUserSavingDtoToUser(userSavingDto);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        user = userDao.save(user);

        UserSavingDto savedUserSavingDto = UserMapper.INSTANCE.convertUserToUserSavingDto(user);

        return savedUserSavingDto;
    }

    public User findByEmail(String email){
        Optional<User> optionalUser  = userDao.findByEmail(email);
        User user = null;
        if(optionalUser.isPresent()){
            user = optionalUser.get();
        }
        return user;
    }


    public Long findIdByUsername(String username) {

        Optional<User> optionalUser  = userDao.findByUsername(username);
        User user = null;

        if(optionalUser.isPresent()){
            user = optionalUser.get();
        }
        return user.getId();
    }

    public User findById(Long id) {
        Optional<User> optionalUser  = userDao.findById(id);
        User user = null;

        if(optionalUser.isPresent()){
            user = optionalUser.get();
        }
        return user;

    }

}
