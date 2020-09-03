package com.udacity.jwdnd.course1.cloudstorage.exception;

public class NoteNotFoundException extends RuntimeException {

    public NoteNotFoundException() {
        super("Note not found.");
    }
}
