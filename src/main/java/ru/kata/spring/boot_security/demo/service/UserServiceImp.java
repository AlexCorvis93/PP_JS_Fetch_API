package ru.kata.spring.boot_security.demo.service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService, UserDetailsService {
    private final UserRepository userRepository;



    public UserServiceImp (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public void add(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> users() {
        return userRepository.findAll();
    }


    @Transactional(readOnly = true)
    @Override
    public User showUser(int id) {
        return userRepository.findById(id).orElse(null);
    }


    @Transactional
    @Override
    public void remove(int id) {
        userRepository.deleteById(id);
    }


    @Transactional
    @Override
    public void update(int id, User user) {
        Optional<User> userById = userRepository.findById(id);
        if (userById.isPresent()) {
            User userFromRepo = userById.get();
            userFromRepo.setId(id);
            userFromRepo.setName(user.getName());
            userFromRepo.setLastname(user.getLastname());
            userFromRepo.setCountry(user.getCountry());
            userFromRepo.setUsername(user.getUsername());
            userFromRepo.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userRepository.save(userFromRepo);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", s));
        }
        return user;
    }

}





