package com.kunikhin.kata313.services;

import com.kunikhin.kata313.dao.RoleDao;

import com.kunikhin.kata313.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    public Role getRole(String rolename) {
        return roleDao.getRole(rolename);
    }

    @Override
    public Role getRoleById(Long id) {
        return roleDao.getRoleById(id);
    }

    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }
}
