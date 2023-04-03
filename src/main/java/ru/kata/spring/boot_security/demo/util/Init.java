package ru.kata.spring.boot_security.demo.util;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
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
//        User user = new User("ADMIN","1",new Role("ADMIN"));
//        User user1 = new User("USER","1",new Role("USER"));
//        userService.save(user);
//        userService.save(user1);
    }
}
