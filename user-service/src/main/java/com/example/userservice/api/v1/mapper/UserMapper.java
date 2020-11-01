package com.example.userservice.api.v1.mapper;

import com.example.userservice.api.v1.domain.UserDto;
import com.example.userservice.model.UserEntity;

public interface UserMapper {
    UserDto userToUserDtoMapper(UserEntity userEntity);
    UserEntity userDtoToUserMapper(UserDto userDto);
}
