package com.example.userservice.api.v1.mapper;

import com.example.userservice.api.v1.domain.UserDto;
import com.example.userservice.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDto userToUserDtoMapper(UserEntity userEntity) {
        if(userEntity == null){
            return null;
        }
        return new UserDto(userEntity.getId(), userEntity.getFirstName(), userEntity.getLastName(), userEntity.getPassword(), userEntity.getEmail());
    }

    @Override
    public UserEntity userDtoToUserMapper(UserDto userDto) {
        if(userDto == null){
            return null;
        }
        return new UserEntity(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getPassword(), userDto.getEmail());
    }
}
