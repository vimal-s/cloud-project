package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.exception.*;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @ExceptionHandler({
    CredentialAlreadyPresentException.class,
    FileAlreadyPresentException.class,
    FileEmptyException.class,
    NoteNotFoundException.class,
    FileNotFoundException.class,
    CredentialNotFoundException.class
  })
  public ModelAndView dataProcessingError(RuntimeException e) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("err", e.getMessage());
    mav.setViewName("result");
    return mav;
  }

  @ExceptionHandler(UserAlreadyPresentException.class)
  public ModelAndView signupError(UserAlreadyPresentException e) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("user", new User());
    mav.addObject("err", e.getMessage());
    mav.setViewName("signup");
    return mav;
  }

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ModelAndView fileSizeError(MaxUploadSizeExceededException e) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("err", "File too large!");
    mav.setViewName("result");
    return mav;
  }

  @ExceptionHandler(Exception.class)
  public ModelAndView handleRemaining(Exception e) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("error", "Unknown Error Occurred");
    mav.setViewName("error");
    return mav;
  }
}
