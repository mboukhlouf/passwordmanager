package com.mboukhlouf.passwordmanager.application.common.exceptions;

public class BadRequestException extends Exception {
    private static final long serialVersionUID = -456426166962069329L;
    
    public BadRequestException(String errorMessage) {
        super(errorMessage);
    }
}