package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleReposirory;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImp implements RoleService{

    private final RoleReposirory roleReposirory;

    public RoleServiceImp(RoleReposirory roleReposirory) {
        this.roleReposirory = roleReposirory;
    }

    @Override
    @Transactional
    public List<Role> getRoles() {
        return roleReposirory.findAll();
    }
}
