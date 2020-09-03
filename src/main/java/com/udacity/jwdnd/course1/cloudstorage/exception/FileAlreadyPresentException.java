package com.udacity.jwdnd.course1.cloudstorage.exception;

public class FileAlreadyPresentException extends RuntimeException {

  public FileAlreadyPresentException() {
    super("File with same name already exists");
  }
}
