package com.mboukhlouf.passwordmanager.application.common.exceptions;

public class NotFoundException extends Exception {
    private static final long serialVersionUID = -2421410483485894313L;

    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }
}