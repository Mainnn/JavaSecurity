package ru.kata.spring.boot_security.demo.servic;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;
@Service
public class RoleServicelmp implements RoleService{


    private final RoleRepository repotiory;

    public RoleServicelmp(RoleRepository repotiory) {
        this.repotiory = repotiory;
    }

    @Override
    public void save(Role role) {
        repotiory.save(role);
    }

    @Override
    public void dell(long id) {
     repotiory.delete(findById(id));
    }

    @Override
    public Role findById(long id) {
        return repotiory.findById(id);
    }

    @Override
    public List<Role> findAll() {
        return repotiory.findAll();
    }

    public Role findRoleById(Long id) {
        return repotiory.findById(id).orElse(null);
    }

}
