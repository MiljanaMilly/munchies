package com.munchies.services.dtoMappers;

import com.munchies.dto.UserDto;
import com.munchies.model.User;
import com.munchies.repositories.RoleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {


    @Autowired
    private RoleJpaRepository roleJpaRepository;

    public User mapUserFormDtoToEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.addRole(roleJpaRepository.findAll().get(0));
        return user;
    }

    public UserDto mapEntityToUserFormDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    public List<UserDto> mapEntitiesToDtoList(List<User> userList) {
        List<UserDto> userDtos = new ArrayList<>();
        for (User u : userList) {
            userDtos.add(new UserMapper().mapEntityToUserFormDto(u));
        }
        return userDtos;

    }

    public List<User> mapDtoListToEntities(List<UserDto> userDtos) {
        List<User> userList = new ArrayList<>();
        for (UserDto userDto : userDtos) {
            userList.add(new UserMapper().mapUserFormDtoToEntity(userDto));
        }
        return userList;


    }
}
