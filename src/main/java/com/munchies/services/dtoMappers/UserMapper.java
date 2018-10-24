package com.munchies.services.dtoMappers;

import com.munchies.dto.UserDto;
import com.munchies.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapUserDtoToEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        return user;
    }

    public UserDto mapEntityToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;

    }
}
