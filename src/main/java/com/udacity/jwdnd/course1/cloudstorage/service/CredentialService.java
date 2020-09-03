package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.exception.CredentialAlreadyPresentException;
import com.udacity.jwdnd.course1.cloudstorage.exception.CredentialNotFoundException;
import com.udacity.jwdnd.course1.cloudstorage.exception.NoteNotFoundException;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final CredentialMapper credentialMapper;
  private final EncryptionService encryptionService;
  private final UserService userService;

  public CredentialService(
      CredentialMapper credentialMapper,
      EncryptionService encryptionService,
      UserService userService) {
    this.credentialMapper = credentialMapper;
    this.encryptionService = encryptionService;
    this.userService = userService;
  }

  public List<Credential> findAll() {
    List<Credential> credentials = credentialMapper.findByUserId(userService.getCurrentUserId());
    credentials.forEach(
        credential ->
            credential.setDecryptedPassword(
                encryptionService.decryptValue(credential.getPassword(), credential.getKey())));
    return credentials;
  }

  public void save(Credential credential) {
    credential.setKey(getSalt());
    String encodedPassword =
        encryptionService.encryptValue(credential.getPassword(), credential.getKey());
    credential.setPassword(encodedPassword);

    if (credential.getId() != 0) {
      isPresent(credential.getId());
      credentialMapper.update(credential);
      return;
    }

    credential.setUserId(userService.getCurrentUserId());
    boolean anyMatch =
        credentialMapper.findByUserId(credential.getUserId()).stream()
            .anyMatch(
                credentialFromDb ->
                    credentialFromDb.getUrl().equalsIgnoreCase(credential.getUrl()));
    if (anyMatch) {
      throw new CredentialAlreadyPresentException();
    }
    credentialMapper.save(credential);
  }

  public void delete(int id) {
    isPresent(id);
    credentialMapper.delete(id);
  }

  private void isPresent(int id) {
    List<Credential> credentials = credentialMapper.findByUserId(userService.getCurrentUserId());
    boolean noneMatch =
            credentials.stream().noneMatch(credentialFromDb -> credentialFromDb.getId() == id);
    if (noneMatch) {
      throw new CredentialNotFoundException();
    }
  }

  private String getSalt() {
    byte[] salt = new byte[16];
    SecureRandom random = new SecureRandom();
    random.nextBytes(salt);
    return Base64.getEncoder().encodeToString(salt);
  }
}
