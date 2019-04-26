package com.java.login.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Base64;

@Service
public class EncrypterDecrypterService {

    public String encryptString(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes());
    }

    public String decryptString(String encryptedValue) {
        return new String(Base64.getDecoder().decode(encryptedValue.getBytes()));
    }

}
