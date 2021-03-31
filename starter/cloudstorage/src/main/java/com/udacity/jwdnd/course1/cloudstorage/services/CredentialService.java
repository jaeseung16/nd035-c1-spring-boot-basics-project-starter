package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public int addCredential(Credential credential) {
        encryptPassword(credential);
        return credentialMapper.insert(credential);
    }

    public Credential getCredential(Integer credentialid) {
        Credential credential = credentialMapper.getCredential(credentialid);
        decryptPassword(credential);
        return credential;
    }

    public List<Credential> getCredentials(Integer userid) {
        List<Credential> credentials = credentialMapper.getCredentials(userid);
        credentials.forEach(this::decryptPassword);
        return credentials;
    }

    private void decryptPassword(Credential credential) {
        String decryptedPassword = encryptionService.decryptValue(credential.getPassword(), credential.getKey());
        credential.setPassword(decryptedPassword);
    }

    private void encryptPassword(Credential credential) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);

        credential.setKey(encodedKey);
        credential.setPassword(encryptedPassword);
    }

    public void updateCredential(Credential credential) {
        encryptPassword(credential);
        credentialMapper.update(credential);
    }

    public void deleteCredential(Integer credentialid) {
        credentialMapper.delete(credentialid);
    }
}
