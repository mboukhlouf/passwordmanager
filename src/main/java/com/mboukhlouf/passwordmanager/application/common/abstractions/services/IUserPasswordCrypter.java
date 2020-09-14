package com.mboukhlouf.passwordmanager.application.common.abstractions.services;

public interface IUserPasswordCrypter {
    String encrypt(String strToEncrypt, String secret);

    String decrypt(String strToDecrypt, String secret);
}
