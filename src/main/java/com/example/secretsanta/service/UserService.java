package com.example.secretsanta.service;

import com.example.secretsanta.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

  public User createUser(User user);

  public Optional<User> getUserById(Long id);

  public List<User> getAllUsers();

  public User updateUser(Long id, User updatedUser);

  public void deleteUser(Long id);
}
