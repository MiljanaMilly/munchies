package com.munchies.services;

import com.munchies.dto.UserFormDto;
import com.munchies.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> getAllUsers();

    User saveUser(UserFormDto user);
}
