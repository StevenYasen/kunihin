package com.kunikhin.kata313.services;

import com.kunikhin.kata313.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();
    User getUserById (long id);
    void addUser(User user);
    void delete(long id);
    void updateUser (long id, User updatedUser);
    UserDetails loadUserByUsername(String username);

}
