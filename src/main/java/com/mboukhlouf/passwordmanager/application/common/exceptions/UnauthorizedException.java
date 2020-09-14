package com.mboukhlouf.passwordmanager.application.common.exceptions;

public class UnauthorizedException extends Exception {
    private static final long serialVersionUID = 4446496265508902297L;

    public UnauthorizedException(String errorMessage) {
        super(errorMessage);
    }
}