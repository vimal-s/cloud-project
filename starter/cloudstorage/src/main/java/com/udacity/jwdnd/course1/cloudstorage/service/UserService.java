package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.exception.UserAlreadyPresentException;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final UserMapper userMapper;
  private final HashService hashService;

  public UserService(UserMapper userMapper, HashService hashService) {
    this.userMapper = userMapper;
    this.hashService = hashService;
  }

  public void save(User user) {
    if (find(user.getUsername()) != null) {
      throw new UserAlreadyPresentException("Username not available");
    }
    user.setSalt(getEncodedSalt());
    user.setPassword(hashService.getHashedValue(user.getPassword(), user.getSalt()));
    logger.info("saving: " + user.toString());
    userMapper.save(user);
  }

  private String getEncodedSalt() {
    byte[] bytes = new byte[16];
    SecureRandom random = new SecureRandom();
    random.nextBytes(bytes);
    return Base64.getEncoder().encodeToString(bytes);
  }

  public User find(String username) {
    return userMapper.find(username);
  }

  public int getCurrentUserId() {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = userMapper.find(username);
    return user.getId();
  }
}
