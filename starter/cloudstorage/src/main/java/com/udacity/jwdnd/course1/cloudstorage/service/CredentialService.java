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
    private final CredentialMapper mapper;
    private final EncryptionService encryptionService;

    public CredentialService(CredentialMapper mapper, EncryptionService encryptionService) {
        this.mapper = mapper;
        this.encryptionService = encryptionService;
    }

    public List<Credential> findAll() {
        List<Credential> credentials = mapper.findAll();
        credentials.forEach(credential -> credential.setPassword(encryptionService.decryptValue(credential.getPassword(), credential.getSalt())));
        return credentials;
    }

    public void save(Credential credential) {
        credential.setSalt(getSalt());
        String encodedPassword = encryptionService.encryptValue(credential.getPassword(), credential.getSalt());
        credential.setPassword(encodedPassword);

        if (credential.getId() != 0) {
            mapper.update(credential);
            return;
        }

        // todo: find userId, replace this
        credential.setUserId(1);
        mapper.save(credential);
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
        mapper.delete(id);
    }
}
