package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService implements AuthenticationProvider {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final UserService userService;
  private final HashService hashService;

  public AuthenticationService(UserService userService, HashService hashService) {
    this.userService = userService;
    this.hashService = hashService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String password = authentication.getCredentials().toString();
    User user = userService.find(username);
    if (user == null) {
      logger.info("Error should be thrown now");
      // todo: how is spring handling this and adding ?error param to url
      throw new UsernameNotFoundException("Username not found.");
    }
    logger.info("user from db: " + user);
    logger.info(
        "user to login: " + authentication.getName() + " " + authentication.getCredentials());
    String encodedValue =
        hashService.getHashedValue((String) authentication.getCredentials(), user.getSalt());
    logger.info("hashedVal: " + encodedValue);
    if (user.getPassword().equals(encodedValue)) {
      logger.info("match found");
      // todo: maybe encoded password should be used
      return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
    }
    throw new BadCredentialsException("match not found");
    //        return null;
  }

  @Override
  public boolean supports(Class<?> aClass) {
    boolean equals = UsernamePasswordAuthenticationToken.class.equals(aClass);
    logger.info("supports: " + equals);
    return equals;
  }
}
