package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.exception.CredentialAlreadyPresentException;
import com.udacity.jwdnd.course1.cloudstorage.exception.FileAlreadyPresentException;
import com.udacity.jwdnd.course1.cloudstorage.exception.UserAlreadyPresentException;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @ExceptionHandler({CredentialAlreadyPresentException.class, FileAlreadyPresentException.class})
  public ModelAndView name(RuntimeException e) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", e);
    mav.setViewName("result");
    return mav;
  }

  @ExceptionHandler(UserAlreadyPresentException.class)
  public ModelAndView signupError(UserAlreadyPresentException e) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", e);
    mav.addObject("user", new User());
    mav.setViewName("signup");
    return mav;
  }
}
