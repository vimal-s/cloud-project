package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final UserService userService;

  public LoginController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/login")
  public String getLoginPage(User user) {
    return "login";
  }

  @GetMapping("/signup")
  public String getSignupPage(User user) {
    return "signup";
  }

  @PostMapping("/signup")
  public String saveUser(User user) {
    userService.save(user);
    return "redirect:/signup?success";
  }
}
