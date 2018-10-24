package com.munchies.dto;

import com.munchies.model.User;

import java.util.ArrayList;
import java.util.List;

public class RoleDto {

    private int id;

    private String name;

    private List<User> users = new ArrayList<>();

    public RoleDto() {
    }

    public RoleDto(List<User> users) {
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
