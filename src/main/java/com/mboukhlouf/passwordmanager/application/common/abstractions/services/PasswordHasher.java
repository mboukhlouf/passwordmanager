package com.mboukhlouf.passwordmanager.application.common.abstractions.services;

public interface PasswordHasher {
    String HashPassword(String password);

    boolean Verify(String text, String hash);
}