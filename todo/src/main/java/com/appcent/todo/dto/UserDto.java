package com.appcent.todo.dto;

import lombok.Data;


@Data
public class UserDto {

    private Long id;
    private String name;
    private String middleName;
    private String surname;
    private String username;
    private String email;
    private String password;
}
