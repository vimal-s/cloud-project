package com.udacity.jwdnd.course1.cloudstorage.exception;

public class UserAlreadyPresentException extends RuntimeException {

  public UserAlreadyPresentException() {
    super("Username already exists");
  }

  public UserAlreadyPresentException(String message) {
    super(message);
  }
}
