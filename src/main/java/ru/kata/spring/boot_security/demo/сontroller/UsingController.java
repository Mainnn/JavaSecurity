package ru.kata.spring.boot_security.demo.сontroller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
        model.addAttribute("users", userService.findAllWithRoles());
        model.addAttribute("updateUser",new User());
        model.addAttribute("userA", userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addAttribute("userEdit", new User());
        model.addAttribute("userCreate", new User());
        model.addAttribute("allRoles", roleService.findAll());
        return "admin";
    }
    @GetMapping(value = "/user")
    public String user(ModelMap model) {
        model.addAttribute("userA", userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addAttribute("user", userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "user";
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

//    @GetMapping("/admin/create_user")
//    public String showCreateUserForm(Model model) {
//        model.addAttribute("userCreate", new User());
//        model.addAttribute("allRoles", roleService.findAll());
//        return "create_user";
//    }

    @PostMapping("/admin/create_user")
    public String createUser(@ModelAttribute("userCreate") User user) {
        userService.save(user);
        return "redirect:/admin";
    }
//    @GetMapping("/admin/saveEdit")
//    public String showEditForm(@RequestParam Long id, Model model) {
//        User user = userService.findById(id);
//        model.addAttribute("userEdit", user);
//        model.addAttribute("roles",roleService.findAll());
//        return "redirect:/admin";
//    }

    @PostMapping("/admin/saveEdit/{id}")
    public String updateUser(@ModelAttribute("userEdit") User user, @PathVariable("id") Long id) throws Exception {
        System.out.println(user.toString());
        user.setId(id);
        userService.save( user);
        return "redirect:/admin";
    }



}
