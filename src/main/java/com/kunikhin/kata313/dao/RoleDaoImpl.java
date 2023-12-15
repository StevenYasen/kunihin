package com.kunikhin.kata313.dao;

import com.kunikhin.kata313.entities.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }

    @Override
    public Role getRole(String rolename) {
        return entityManager.createQuery("select r from Role r where r.rolename = :rolename", Role.class).setParameter("rolename",rolename).getSingleResult();
//        TypedQuery<Role> query = entityManager.createQuery("select r from Role r where r.rolename = :rolename", Role.class);
//        query.setParameter("rolename",rolename);
//        return query.getSingleResult();
    }

    @Override
    public Role getRoleById(Long id) {
        return entityManager.createQuery("select r from Role r where r.id = :id", Role.class).setParameter("id",id).getSingleResult();
//        TypedQuery<Role> query = entityManager.createQuery("select r from Role r where r.id = :id", Role.class);
//        query.setParameter("id",id);
//        return query.getSingleResult();
    }

    @Override
    public void addRole(Role role)  {
        entityManager.persist(role);
    }
}
