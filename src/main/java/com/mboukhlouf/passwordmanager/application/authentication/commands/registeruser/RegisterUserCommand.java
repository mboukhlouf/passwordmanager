package com.mboukhlouf.passwordmanager.application.authentication.commands.registeruser;

import com.mboukhlouf.passwordmanager.application.jmediatr.Request;

public class RegisterUserCommand implements Request<Integer> {
    private String username;
    private String password;

    public RegisterUserCommand() {
    }

    public RegisterUserCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    /**
     * @return String return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return String return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}