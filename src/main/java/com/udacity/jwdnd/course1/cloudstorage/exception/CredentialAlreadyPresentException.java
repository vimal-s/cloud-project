package com.udacity.jwdnd.course1.cloudstorage.exception;

public class CredentialAlreadyPresentException extends RuntimeException {

  public CredentialAlreadyPresentException() {
    super("Credential with same url already exists");
  }
}
