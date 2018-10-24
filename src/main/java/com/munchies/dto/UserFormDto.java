package com.munchies.dto;

import com.munchies.model.Role;

import java.util.ArrayList;
import java.util.List;

public class UserFormDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private List<RoleDto> roles = new ArrayList<>();

    public UserFormDto() {
    }

    public UserFormDto(Long id, String firstName, String lastName, String email, String password, List<RoleDto> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }


}
