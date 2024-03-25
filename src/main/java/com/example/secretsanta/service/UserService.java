package com.example.secretsanta.service;

import java.util.List;

import com.example.secretsanta.model.User;

public interface UserService {

    public User createUser(User user);

    public User getUserById(Long id);

    public List<User> getAllUsers();

    public User updateUser(Long id, User updatedUser);

    public void deleteUser(Long id);

}
