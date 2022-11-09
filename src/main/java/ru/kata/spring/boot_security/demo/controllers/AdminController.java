package ru.kata.spring.boot_security.demo.controllers;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String mainAdmin(Model m, @AuthenticationPrincipal User current) {
        m.addAttribute("roles", roleService.getRoles());
        m.addAttribute("users", userService.users());
        m.addAttribute("current", current);
        return "admin/main";
    }

    @GetMapping("/new")
    public String newUser(ModelMap m, @AuthenticationPrincipal User current) {
        m.addAttribute("roles", roleService.getRoles());
        m.addAttribute("user", new User());
        m.addAttribute("current", current);
        return "admin/new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model m) {
        m.addAttribute("roles", roleService.getRoles());
        if (bindingResult.hasErrors()) {
            return "admin/new";
        }
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(ModelMap m,  @PathVariable("id") int id) {
        m.addAttribute("one", userService.showUser(id));
        return "admin/main";

    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "user/edit";
        }
        userService.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.remove(id);
        return "redirect:/admin";
    }
}
