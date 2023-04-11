package ru.kata.spring.boot_security.demo.servic;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service

public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    private final BCryptPasswordEncoder encoder;

    public UserServiceImp(UserRepository userRepository, RoleService roleService, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.encoder = encoder;
    }

    @Override
    public void del(Long id) {
        userRepository.delete(findById(id));
    }


    @Override
    public List<User> findAllWithRoles() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findUserByUsername(name);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAllBy();
    }


    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        Set<Role> userRoles = new HashSet<>();

        for (Role role : user.getRoles()) {
            Role existingRole = roleService.findByName(role.getName());
            if (existingRole == null) {
                // Если роль не существует, сохраните новую роль и добавьте ее к пользователю
                roleService.save(role);
                userRoles.add(role);
            } else {
                // Если роль существует, просто добавьте ее к пользователю
                userRoles.add(existingRole);
            }
        }

        user.setRoles(userRoles);
        userRepository.save(user);
    }
}
