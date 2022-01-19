package ru.kata.spring.boot_security.demo.servic;



import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void save(User user);
    void del(Long id);
    List<User> findAll();
    User findById(long id);
    User findByName(String username);
    String getLoggedInUsername ();


}
