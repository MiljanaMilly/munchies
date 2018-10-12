package com.munchies.model;


import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roles_id;

    @ManyToMany(mappedBy = "roles")
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public int getRoles_id() {
        return roles_id;
    }

    public void setRoles_id(int roles_id) {
        this.roles_id = roles_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
