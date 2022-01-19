package ru.kata.spring.boot_security.demo.сontroller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.servic.RoleService;
import ru.kata.spring.boot_security.demo.servic.UserService;
import ru.kata.spring.boot_security.demo.model.User;

@Controller
public class UsingController {
    private final UserService userService;
    private final RoleService roleService;

    public UsingController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/admin")
    public String printWelcome(ModelMap model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("updateUser",new User());
        return "userss";
    }
    @GetMapping(value = "/user")
    public String user(ModelMap model) {
        model.addAttribute("users", userService.findByName(userService.getLoggedInUsername()));
        return "userss";
    }
    @PostMapping(value = "/admin/del")
    public String delUser(@RequestParam Long id, ModelMap model){
        userService.del(id);
        return "redirect:/admin";
    }
    @PostMapping(value = "/admin/add")
    public String addUser(@ModelAttribute("updateUser")User user){
        userService.save(user);
        return "redirect:/admin";
    }
    @PostMapping(value = "/admin/update")
    public String updateUser(@ModelAttribute("updateUser")User user){
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/create_user")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.findAll());
        return "create_user";
    }

    @PostMapping("/admin/create_user")
    public String createUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }
    @GetMapping("/admin/edit")
    public String showEditForm(@RequestParam Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles",roleService.findAll());
        return "edit";
    }

    @PostMapping("/admin/saveEdit")
    public String updateUser(@RequestParam Long id, @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        user.setId(id);
        userService.save(user);
        return "redirect:/admin";
    }



}
