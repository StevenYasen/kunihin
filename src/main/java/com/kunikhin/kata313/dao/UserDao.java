package com.kunikhin.kata313.dao;

import com.kunikhin.kata313.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;


public interface UserDao {
    List<User> getAllUsers();
    User getUserById (long id);

    void addUser(User user);

    void delete(long id);
    void updateUser (long id, User updatedUser);
    public User loadUserByUsername(String username);

}
