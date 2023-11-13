package com.kunikhin.kata313.dao;

import com.kunikhin.kata313.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById (long id) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.id = :id", User.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void updateUser (long id, User updatedUser) {
        User user = entityManager.find(User.class, id);
        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());
        user.setEmail(updatedUser.getEmail());
        user.setAge(updatedUser.getAge());
        entityManager.persist(user);
    }

    @Override
    public User loadUserByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.username = :username", User.class);
        query.setParameter("username",username);
        return query.getSingleResult();
    }
}
