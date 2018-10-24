package com.munchies.services;

import com.munchies.dto.UserFormDto;
import com.munchies.model.Role;
import com.munchies.model.User;
import com.munchies.repositories.RoleJpaRepository;
import com.munchies.repositories.UserJpaRepository;
import com.munchies.services.dtoMappers.RoleMapper;
import com.munchies.services.dtoMappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

    @Autowired
    private RoleMapper roleMapper;

    public List<User> getAllUsers() {
        return userJpaRepository.findAll();
    }

    @Override
    public User saveUser(UserFormDto user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User saveUser = userMapper.mapUserFormDtoToEntity(user);
//      saveUser.setRoles();
        Role r = roleJpaRepository.findAll().get(0);
        r.setId(1);
        saveUser.setRoles(Arrays.asList(r));
        return userJpaRepository.save(saveUser);
        //validacija,ukoliko postoji user sa istim emailom, ne moze da se cuva
    }

}


