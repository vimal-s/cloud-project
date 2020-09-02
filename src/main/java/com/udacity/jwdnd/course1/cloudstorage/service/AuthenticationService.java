package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService implements AuthenticationProvider {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final HashService hashService;
  private final UserService userService;

  public AuthenticationService(UserService userService, HashService hashService) {
    this.userService = userService;
    this.hashService = hashService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    User user = userService.find(authentication.getName());
    if (user == null) {
      throw new UsernameNotFoundException("Username not found.");
    }
    String encodedPassword =
        hashService.getHashedValue((String) authentication.getCredentials(), user.getSalt());
    if (user.getPassword().equals(encodedPassword)) {
      return new UsernamePasswordAuthenticationToken(
          authentication.getName(), authentication.getCredentials(), new ArrayList<>());
    }
    return null;
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return UsernamePasswordAuthenticationToken.class.equals(aClass);
  }
}
