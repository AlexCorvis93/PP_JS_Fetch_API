package ru.kata.spring.boot_security.demo.controllers;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class RestAdminController {

    private final UserService userService;
    private final RoleService roleService;

    private final ModelMapper modelMapper;

    public RestAdminController(UserService userService, RoleService roleService, ModelMapper modelMapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }


    private UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }


    @GetMapping()
    public ResponseEntity<List<UserDTO>> mainAdmin() {
        return new ResponseEntity<>(userService.users().stream().map(this::convertToDTO).collect(Collectors.toList()), HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.add(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        User user = userService.showUser(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody User user,  @PathVariable("id") int id) {
        userService.update(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        userService.remove(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/user")
    public ResponseEntity<User> showUser(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<>(roleService.getRoles(), HttpStatus.OK);
    }



}
