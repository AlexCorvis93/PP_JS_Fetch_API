package ru.kata.spring.boot_security.demo.dto;
import ru.kata.spring.boot_security.demo.models.Role;
import java.util.HashSet;
import java.util.Set;

public class UserDTO {

    private int id;

    private String name;
    private String username;
    private String lastname;
    private String country;
    private Set<String> roles;


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = getUserRoles(roles);
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getUserRoles(Set<Role> roles) {
        Set<String> roleList = new HashSet<>();
        for (Role r : roles) {
            roleList.add(r.toString());
        }
        return roleList;
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
}
