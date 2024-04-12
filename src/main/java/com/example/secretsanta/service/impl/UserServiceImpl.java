package com.example.secretsanta.service.impl;

import com.example.secretsanta.aop.Logged;
import com.example.secretsanta.cache.EntityCache;
import com.example.secretsanta.dto.user.UserInfoDto;
import com.example.secretsanta.exception.BadRequestException;
import com.example.secretsanta.exception.NotFoundException;
import com.example.secretsanta.model.User;
import com.example.secretsanta.repository.UserRepository;
import com.example.secretsanta.service.UserService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;
  private EntityCache<User> userCache;
  private ModelMapper modelMapper;

  private String userErrorMessage = "There is no user with id=";

  @Autowired
  public UserServiceImpl(
      UserRepository userRepository, EntityCache<User> userCache, ModelMapper modelMapper) {
    this.userRepository = userRepository;
    this.userCache = userCache;
    this.modelMapper = modelMapper;
  }

  @Logged
  @Override
  public Optional<UserInfoDto> createUser(User user) {
    if (user.getName() == null || user.getName().equals("")) {
      throw new BadRequestException("Wrong user name");
    }
    try {
      userCache.put(user.getId(), user);
      return Optional.of(
          modelMapper.map(
              userRepository.save(modelMapper.map(user, User.class)), UserInfoDto.class));
    } catch (Exception e) {
      throw new BadRequestException("Wrong user parameters");
    }
  }

  @Logged
  @Override
  public Optional<UserInfoDto> getUserById(Long userId) {
    Optional<User> user = userCache.get(userId);
    if (!user.isPresent()) {
      user = userRepository.findById(userId);
      if (user.isPresent()) {
        userCache.put(userId, user.get());
      } else {
        throw new NotFoundException(userErrorMessage, userId);
      }
    }
    return Optional.of(modelMapper.map(user.get(), UserInfoDto.class));
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
