package com.kunikhin.kata313.dao;

import com.kunikhin.kata313.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    @Lazy
    public UserDaoImpl(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(long id) {
        return entityManager.createQuery("select u from User u where u.id = :id", User.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.persist(user);
    }

    @Override
    public void delete(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void updateUser(long id, User updatedUser) {
        User user = entityManager.find(User.class, id);
        user.setUsername(updatedUser.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(user.getLastName());
        user.setAge(updatedUser.getAge());
        entityManager.persist(user);
    }

    @Override
    public User loadUserByUsername(String username) {
        return entityManager.createQuery("select u from User u join fetch u.roles where u.username = :username", User.class).setParameter("username", username).getSingleResult();
    }

}
