package ru.kata.spring.boot_security.demo.сontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.servic.RoleService;
import ru.kata.spring.boot_security.demo.servic.UserService;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class RestAdminController {
    private  final UserService userService;

   private final RoleService roleService;

    public RestAdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/allUsers")
    @ResponseBody
    public ResponseEntity<List<User>> getAllUsers() {
        for (User user:userService.findAll()) {
            System.out.println(user.toString());
        }
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/createUser")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        System.out.println("TEST");
        System.out.println(user.toString());
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.del(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PatchMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") Long id) {
        User temp = userService.findById(id);
        temp.setUsername(user.getUsername());
        temp.setPassword(user.getPassword());
        temp.setRoles(user.getRoles());
        userService.save(temp);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }
}
