package ru.kata.spring.boot_security.demo.util;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.servic.UserService;
@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {

    private final UserService userService;

    public Init(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        User user = userService.createUserWithRole("ADMIN","1","ADMIN");
//        User user1 = userService.createUserWithRole("USER","1","USER");
    }
}
