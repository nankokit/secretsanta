package com.example.secretsanta.service;

import java.util.List;
import java.util.Optional;

import com.example.secretsanta.model.User;

public interface UserService {

    public User createUser(User user);

    public Optional<User> getUserById(Long id);

    public List<User> getAllUsers();

    public User updateUser(Long id, User updatedUser);

    public void deleteUser(Long id);
}
