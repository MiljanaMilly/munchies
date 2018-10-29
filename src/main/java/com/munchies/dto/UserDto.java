package com.munchies.dto;

import com.munchies.model.Role;
import com.munchies.validators.FieldMatch;
import com.munchies.validators.SignUpValidator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@FieldMatch
public class UserDto {

    private Long id;

    @NotNull
    @Size(min = 5, max = 10)
    private String firstName;

    @Size(min = 5, max = 10)
    @NotNull
    private String lastName;

    @NotNull
    @Email
    @SignUpValidator
    private String email;

    @NotNull
    @Size(min = 7, max = 15)
    private String password;

    @NotNull
    @Size(min = 7, max = 15)
    private String confirmPassword;

    private List<Role> roles = new ArrayList<>();

    public UserDto() {
    }

    public UserDto(Long id, String firstName, String lastName, String email, String password, List<Role> roles) {
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        roles.add(role);
    }


}
