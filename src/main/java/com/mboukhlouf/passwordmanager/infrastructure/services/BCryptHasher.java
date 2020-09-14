package com.mboukhlouf.passwordmanager.infrastructure.services;

import com.mboukhlouf.passwordmanager.application.common.abstractions.services.PasswordHasher;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class BCryptHasher implements PasswordHasher {

    @Override
    public String HashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean Verify(String text, String hash) {
        return BCrypt.checkpw(text, hash);
    }
    
}
