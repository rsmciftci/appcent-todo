package com.appcent.todo.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class UserSavingDto {


    @NotNull
    private String name;
    private String middleName;
    @NotNull
    private String surname;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;

}
