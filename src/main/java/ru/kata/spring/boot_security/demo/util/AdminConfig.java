package ru.kata.spring.boot_security.demo.util;


import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;

import java.util.HashSet;
import java.util.Set;


//@Component
//public class AdminConfig {
//    private final UserService userService;
//
//
//    public AdminConfig(UserService userService) {
//        this.userService = userService;
//    }
//
//    @PostConstruct
//    public void initDbUsers() {
//        Role roleAdmin = new Role("ROLE_ADMIN");
//        Role roleUser = new Role("ROLE_USER");
//
//        User admin = new User();
//        Set<Role> adminRoles = new HashSet<>();
//        adminRoles.add(roleAdmin);
//        admin.setId(1);
//        admin.setUsername("admin");
//        admin.setPassword("1234");
//        admin.setName("sanya");
//        admin.setLastname("dude");
//        admin.setCountry("RF");
//        admin.setRoles(adminRoles);
//        userService.add(admin);
//
//        User user1 = new User();
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(roleUser);
//        user1.setId(2);
//        user1.setUsername("user1");
//        user1.setPassword("12345");
//        user1.setName("Gosha");
//        user1.setLastname("dudev");
//        user1.setCountry("RF");
//        user1.setRoles(userRoles);
//        userService.add(user1);
//
//    }
//}
