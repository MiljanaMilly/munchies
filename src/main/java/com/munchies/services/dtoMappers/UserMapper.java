package com.munchies.services.dtoMappers;

import com.munchies.dto.UserFormDto;
import com.munchies.dto.UserListDto;
import com.munchies.model.User;
import com.munchies.repositories.RoleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleJpaRepository roleJpaRepository;

    public User mapUserListDtoToEntity(UserListDto userListDto) {
        User user = new User();
        user.setId(userListDto.getId());
        user.setFirstName(userListDto.getFirstName());
        user.setLastName(userListDto.getLastName());
        user.setEmail(userListDto.getEmail());
        return user;
    }

    public UserListDto mapEntityToUserListDto(User user) {
        UserListDto userListDto = new UserListDto();
        userListDto.setId(user.getId());
        userListDto.setFirstName(user.getFirstName());
        userListDto.setLastName(user.getLastName());
        userListDto.setEmail(user.getEmail());
        return userListDto;
    }

    public User mapUserFormDtoToEntity(UserFormDto userFormDto) {
        User user = new User();
        user.setId(userFormDto.getId());
        user.setFirstName(userFormDto.getFirstName());
        user.setLastName(userFormDto.getLastName());
        user.setEmail(userFormDto.getEmail());
        user.setPassword(userFormDto.getPassword());
        user.addRole(roleJpaRepository.findAll().get(0));
        return user;
    }

    public UserFormDto mapEntityToUserFormDto(User user) {
        UserFormDto userFormDto = new UserFormDto();
        userFormDto.setId(user.getId());
        userFormDto.setFirstName(user.getFirstName());
        userFormDto.setLastName(user.getLastName());
        userFormDto.setEmail(user.getEmail());
        userFormDto.setPassword(user.getPassword());
        return userFormDto;
    }
}
