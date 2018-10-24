package com.munchies.services.dtoMappers;

import com.munchies.dto.RoleDto;
import com.munchies.model.Role;
import com.munchies.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleMapper {

    public Role mapRoleDtoToEntity(RoleDto roleDto) {
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());
        role.setUsers(roleDto.getUsers());
        return role;
    }

    public RoleDto mapEntityToRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        roleDto.setUsers(role.getUsers());
        return roleDto;

    }

    public List<Role> rolesFromDto(List<RoleDto> rolesDto) {
        RoleMapper rm = new RoleMapper();
        List<Role> list = new ArrayList<>();
        for (RoleDto r : rolesDto) {
            list.add(rm.mapRoleDtoToEntity(r));
        }
        return list;

    }

    public List<RoleDto> rolesFromEntity(List<Role> roles) {
        RoleMapper rm = new RoleMapper();
        List<RoleDto> list = new ArrayList<>();
        for (Role r : roles) {
            list.add(rm.mapEntityToRoleDto(r));
        }
        return list;

    }
}
