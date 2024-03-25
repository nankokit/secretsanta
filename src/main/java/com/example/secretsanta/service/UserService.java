package com.example.secretsanta.service;

import java.util.List;

import com.example.secretsanta.model.User;

public interface UserService {

    public User createUser(User user);

    public static User getUserById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserById'");
    }

    public List<User> getAllUsers();

    public User updateUser(Long id, User updatedUser);

    public void deleteUser(Long id);

}
