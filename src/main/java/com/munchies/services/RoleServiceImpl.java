package com.munchies.services;

import com.munchies.model.Role;
import com.munchies.repositories.RoleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleJpaRepository roleJpaRepository;

    public List<Role> findAll() {
        return roleJpaRepository.findAll();

    }
}
