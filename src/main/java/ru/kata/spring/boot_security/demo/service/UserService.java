package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    void add(User user);

    List<User> users();

    User showUser(int id);

    void remove(int id);

    void update(int id, User user);
}
