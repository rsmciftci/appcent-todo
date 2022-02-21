package com.appcent.todo.converter;

import com.appcent.todo.dto.UserDto;
import com.appcent.todo.dto.UserSavingDto;
import com.appcent.todo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source="email", target = "email")
    @Mapping(source="email", target = "username")
    User convertUserSavingDtoToUser(UserSavingDto userSavingDto);

    UserSavingDto convertUserToUserSavingDto(User user);

    UserDto convertUserToUserDto(User user);
}
