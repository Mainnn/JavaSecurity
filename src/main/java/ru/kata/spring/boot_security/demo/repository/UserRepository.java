package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {
    User findById(long id);
    //User findUserByUsername(String name);
    @Query("SELECT u FROM User u JOIN FETCH u.roles")
    List<User> findAllBy();
    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.username = ?1")
    User findUserByUsername(String name);


}
