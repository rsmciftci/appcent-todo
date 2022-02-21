package com.appcent.todo.service;

import com.appcent.todo.converter.UserMapper;
import com.appcent.todo.dao.UserDao;
import com.appcent.todo.dataprovider.BcryptProvider;
import com.appcent.todo.dataprovider.UserDataProvider;
import com.appcent.todo.dto.UserSavingDto;
import com.appcent.todo.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserDao userDao;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void save() {

        UserSavingDto userSavingDto = UserDataProvider.getUserSavingDto();
        User user = UserMapper.INSTANCE.convertUserSavingDtoToUser(userSavingDto);
        String encodedPassword = BcryptProvider.encode(user.getPassword());
        user.setPassword(encodedPassword);

        when(userDao.save(ArgumentMatchers.any(User.class))).thenReturn(user);
        UserSavingDto result = userService.save(userSavingDto);
        result.setPassword(encodedPassword);
        assertEquals(userSavingDto,result);

    }

    @Test
    void findIdByUsername() {

        User user = UserDataProvider.getUser();
        when(userDao.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        assertFalse(Optional.of(user).isEmpty());
        Long userId = user.getId();
        Long result = userService.findIdByUsername(user.getUsername());
        assertEquals(userId,result);

    }
}