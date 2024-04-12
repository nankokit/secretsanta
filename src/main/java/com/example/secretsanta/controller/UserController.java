package com.example.secretsanta.controller;

import com.example.secretsanta.dto.user.UserInfoDto;
import com.example.secretsanta.model.User;
import com.example.secretsanta.service.UserService;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

  private UserService userService;

  @PostMapping
  public Optional<UserInfoDto> createUser(@RequestBody User user) {
    return userService.createUser(user);
  }

  @PutMapping("/{userId}")
  public User updateUser(@PathVariable Long userId, @RequestBody User user) {
    user.setId(userId);
    return userService.updateUser(userId, user);
  }

  @DeleteMapping("/{userId}")
  public void deleteUser(@PathVariable Long userId) {
    userService.deleteUser(userId);
  }

  @GetMapping("/{userId}")
  public Optional<UserInfoDto> getUserById(@PathVariable Long userId) {
    return userService.getUserById(userId);
  }

  @GetMapping("/getAll")
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }
}
