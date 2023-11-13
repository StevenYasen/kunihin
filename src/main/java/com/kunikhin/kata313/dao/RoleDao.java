package com.kunikhin.kata313.dao;

import com.kunikhin.kata313.entities.Role;

import java.util.List;

public interface RoleDao {
    List<Role> getAllRoles();

    Role getRole(String rolename);

    Role getRoleById(Long id);

    void addRole(Role role);
}
