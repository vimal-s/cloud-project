package com.udacity.jwdnd.course1.cloudstorage.exception;

public class NoteNotFoundException extends RuntimeException {

    public NoteNotFoundException() {
        super("Either note does not exist or User not authorized");
    }
}
