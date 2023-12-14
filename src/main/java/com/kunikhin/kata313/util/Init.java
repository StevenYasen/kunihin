package com.kunikhin.kata313.util;

import com.kunikhin.kata313.entities.Role;
import com.kunikhin.kata313.entities.User;
import com.kunikhin.kata313.services.RoleService;
import com.kunikhin.kata313.services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
public class Init {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public Init(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void initializedDataBase() {
        roleService.addRole(new Role("ADMIN"));
        roleService.addRole(new Role("USER"));

        Set<Role> adminRole = new HashSet<>();
        Set<Role> userRole = new HashSet<>();
        Set<Role> allRoles = new HashSet<>();

        adminRole.add(roleService.getRoleById(1L));
        userRole.add(roleService.getRoleById(2L));
        allRoles.add(roleService.getRoleById(1L));
        allRoles.add(roleService.getRoleById(2L));

        userService.addUser(new User("admin@admin.ru", "admin", "Admin", "Adminov", (byte) 44, adminRole));
        userService.addUser(new User("user@user.ru", "user", "User", "Userov", (byte) 56, userRole));
        userService.addUser(new User("all@all.ru", "all", "All", "Allin", (byte) 16, allRoles));

    }
}
