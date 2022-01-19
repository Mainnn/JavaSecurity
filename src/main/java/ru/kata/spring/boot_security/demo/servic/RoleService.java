package ru.kata.spring.boot_security.demo.servic;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {

    void save(Role role);
    void dell(long id);
    Role findById(long id);
    List<Role> findAll();
    Role findRoleById(Long id);
}
