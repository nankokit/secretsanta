package com.example.secretsanta.service;

import com.example.secretsanta.dto.user.UserInfoDto;
import com.example.secretsanta.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

  public Optional<UserInfoDto> createUser(User user);

  public Optional<UserInfoDto> getUserById(Long id);

  public List<User> getAllUsers();

  public User updateUser(Long id, User updatedUser);

  public void deleteUser(Long id);
}
