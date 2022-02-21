package com.appcent.todo.dataprovider;

import com.appcent.todo.dto.UserRequestDto;
import com.appcent.todo.dto.UserSavingDto;
import com.appcent.todo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserDataProvider {



    public static User getUser() {
        User user = new User();
        user.setId(1L);
        user.setSurname("ÇİFTÇİ");
        user.setName("Rasim");
        user.setEmail("rsmciftci@gmail.com");
        user.setPassword("123456");
        user.setUsername(user.getEmail());

        return user;
    }

    public static UserSavingDto getUserSavingDto() {
        UserSavingDto userSavingDto = new UserSavingDto();
        userSavingDto.setEmail("rsmciftci@gmail.com");
        userSavingDto.setPassword("123456");
        userSavingDto.setName("Rasim");
        userSavingDto.setSurname("ÇİFTÇİ");

        return userSavingDto;
    }

    public static UserRequestDto getUserRequestDto() {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setUsername("rsmciftci@gmail.com");
        userRequestDto.setPassword("123456");

        return userRequestDto;
    }
}
