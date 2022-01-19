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
    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder encoder;

    public UserServiceImp(UserRepository userRepository, RoleService roleService, RoleRepository roleRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @Override
    public void del(Long id) {
        userRepository.delete(findById(id));
    }


    @Override
    public List<User> findAll() {
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
    public String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else {
                return principal.toString();
            }
        }
        return null;
    }
//используеться в Init


    @Override
    @Transactional
    public void save(User user) {
        System.out.println(user.toString());
        user.setPassword(encoder.encode(user.getPassword()));
        Set<Role> userRoles = new HashSet<>();

        for (Role role : user.getRoles()) {
            Role temp = roleService.findRoleById(role.getId());
            if (temp == null) {
                temp = new Role();
                temp.setName(role.getName());
                temp = roleRepository.save(temp);
            }
            userRoles.add(temp);

        }

        user.setRoles(userRoles);
        userRepository.save(user);
    }
}
