package com.example.secretsanta.service.impl;

import com.example.secretsanta.aop.Logged;
import com.example.secretsanta.cache.EntityCache;
import com.example.secretsanta.exception.BadRequestException;
import com.example.secretsanta.exception.NotFoundException;
import com.example.secretsanta.model.User;
import com.example.secretsanta.repository.UserRepository;
import com.example.secretsanta.service.UserService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;
  private EntityCache<User> userCache;

  @Logged
  @Override
  public User findByNameAndPassword(String name, String password) {
    return userRepository
        .findByNameAndPassword(name, password)
        .orElseThrow(() -> new NoSuchElementException("User not found"));
  }

  @Logged
  @Override
  public User createUser(User user) {
    if (user.getName() == null || user.getName().equals("")) {
      throw new BadRequestException("Wrong user name");
    }
    userCache.put(user.getId(), user);
    return userRepository.save(user);
  }

  public List<User> bulkCreateUser(List<User> users) {
    if (users.stream().anyMatch(u -> (u.getName() == null || u.getName().equals("")))) {
      throw new BadRequestException("Wrong user(s) name(s)");
    }
    return users.stream().map(u -> userRepository.save(u)).toList();
  }

  @Logged
  @Override
  public Optional<User> getUserById(Long userId) {
    Optional<User> user = userCache.get(userId);
    if (!user.isPresent()) {
      user = userRepository.findById(userId);
      if (user.isPresent()) {
        userCache.put(userId, user.get());
      } else {
        throw new NotFoundException("There is no user with id=", userId);
      }
    }
    return user;
  }

  @Logged
  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Logged
  @Override
  public User updateUser(Long userId, User updatedUser) {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new NoSuchElementException("User not found"));
    user.setName(updatedUser.getName());
    user.setEmail(updatedUser.getEmail());
    user.setPassword(updatedUser.getPassword());

    userCache.put(userId, user);
    return userRepository.save(user);
  }

  @Logged
  @Override
  public void deleteUser(Long userId) {
    userCache.remove(userId);
    userRepository.deleteById(userId);
  }
}
