package com.munchies.services;

import com.munchies.dto.UserDto;
import com.munchies.exceptions.EmailExistsException;
import com.munchies.model.Role;
import com.munchies.model.User;
import com.munchies.repositories.RoleJpaRepository;
import com.munchies.repositories.UserJpaRepository;
import com.munchies.services.dtoMappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleJpaRepository roleJpaRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDto> getAllDtoUsers() {
        List<User> list = userJpaRepository.findAll();
        return userMapper.mapEntitiesToDtoList(list);
    }

    @Override
    public User saveUser(UserDto user) throws EmailExistsException {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User saveUser = userMapper.mapUserFormDtoToEntity(user);
        List<Role> r = roleJpaRepository.findAll();
        saveUser.setRoles(r);
        if ((userJpaRepository.findByEmail(saveUser.getEmail())) == null) {
            saveUser = userJpaRepository.save(saveUser);
        } else {
            throw new EmailExistsException("Email exists in the database");
        }

        return saveUser;
    }

}


