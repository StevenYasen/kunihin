package com.kunikhin.kata313.services;

import com.kunikhin.kata313.entities.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    Role getRole(String rolename);

    Role getRoleById(Long id);

    void addRole(Role role);
}
