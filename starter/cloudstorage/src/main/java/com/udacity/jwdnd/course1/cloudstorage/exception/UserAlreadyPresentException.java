package com.udacity.jwdnd.course1.cloudstorage.exception;

public class UserAlreadyPresentException extends RuntimeException {

  public UserAlreadyPresentException() {
    super("Username exists in the database");
  }

  public UserAlreadyPresentException(String message) {
    super(message);
  }
}
