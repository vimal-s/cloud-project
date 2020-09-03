package com.udacity.jwdnd.course1.cloudstorage.exception;

public class FileNotFoundException extends RuntimeException {

    public FileNotFoundException() {
        super("File not found");
    }
}
