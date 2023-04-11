package ru.kata.spring.boot_security.demo.сontroller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.servic.UserService;

import java.net.Authenticator;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestUserController {

    private final UserService userService;

    public RestUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    @ResponseBody
    public User loginUser(){
        System.out.println(userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName()).toString());
        return userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());

    }


}
