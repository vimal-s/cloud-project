package com.udacity.jwdnd.course1.cloudstorage.exception;

public class FileEmptyException extends RuntimeException {

  public FileEmptyException() {
    super("Empty file not allowed");
  }
}
