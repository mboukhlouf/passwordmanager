package com.mboukhlouf.passwordmanager.application.passwords.commands.DeletePassword;

import com.mboukhlouf.passwordmanager.application.jmediatr.Request;

public class DeletePasswordCommand implements Request<Integer> {
    private Integer passwordId;

    public DeletePasswordCommand(Integer passwordId) {
        this.passwordId = passwordId;
    }

    /**
     * @return Integer return the passwordId
     */
    public Integer getPasswordId() {
        return passwordId;
    }

    /**
     * @param passwordId the passwordId to set
     */
    public void setPasswordId(Integer passwordId) {
        this.passwordId = passwordId;
    }
}
