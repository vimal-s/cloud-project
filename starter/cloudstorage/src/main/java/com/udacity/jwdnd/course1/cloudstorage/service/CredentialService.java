package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
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

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService, UserService userService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
        this.userService = userService;
    }

    public List<Credential> findAllByUser() {
        List<Credential> credentials = credentialMapper.findByUserId(userService.getCurrentUserId());
        credentials.forEach(credential -> credential.setPassword(encryptionService.decryptValue(credential.getPassword(), credential.getSalt())));
        return credentials;
    }

    public void save(Credential credential) {
        credential.setSalt(getSalt());
        String encodedPassword = encryptionService.encryptValue(credential.getPassword(), credential.getSalt());
        credential.setPassword(encodedPassword);

        if (credential.getId() != 0) {
            credentialMapper.update(credential);
            return;
        }

        credential.setUserId(userService.getCurrentUserId());
        credentialMapper.save(credential);
    }

    private String getSalt() {
        byte[] salt = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        logger.info("salt is: " + Arrays.toString(salt));
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        logger.info("encoded salt: " + encodedSalt);
        return encodedSalt;
    }

    public void delete(int id) {
        credentialMapper.delete(id);
    }
}
