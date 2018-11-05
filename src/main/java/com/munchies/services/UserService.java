package com.munchies.services;

import com.munchies.dto.UserDto;
import com.munchies.exceptions.EmailExistsException;
import com.munchies.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<UserDto> getAllDtoUsers();

    User saveUser(UserDto user) throws EmailExistsException;
}
