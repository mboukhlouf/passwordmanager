package com.mboukhlouf.passwordmanager.application.common.abstractions.services;

public interface ICurrentUserService {
    boolean isAuthenticated();

    Integer getId();

    String getUsername();

    String getPassword();
}
