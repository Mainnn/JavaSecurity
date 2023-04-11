package ru.kata.spring.boot_security.demo.servic;



import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void save(User user);
    void del(Long id);
    @EntityGraph(attributePaths = {"roles"})
    @Query("SELECT u FROM User u")
    List<User> findAllWithRoles();
    User findById(long id);
    User findByName(String username);
    List<User> findAll();
}
