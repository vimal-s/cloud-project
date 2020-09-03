package com.udacity.jwdnd.course1.cloudstorage.exception;

public class CredentialNotFoundException extends RuntimeException {

    public CredentialNotFoundException() {
        super("Credential not found");
    }
}
