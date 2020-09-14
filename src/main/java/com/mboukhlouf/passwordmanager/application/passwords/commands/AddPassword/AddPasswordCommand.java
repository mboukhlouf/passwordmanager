package com.mboukhlouf.passwordmanager.application.passwords.commands.AddPassword;

import com.mboukhlouf.passwordmanager.application.jmediatr.Request;

public class AddPasswordCommand implements Request<Integer> {
    private String label;
    private String account;
    private String password;
    private String url;
    private String tags;

    public AddPasswordCommand(String label, String account, String password, String url, String tags) {
        this.label = label;
        this.account = account;
        this.password = password;
        this.url = url;
        this.tags = tags;
    }

    /**
     * @return String return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return String return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(String account) {
        this.account = account;
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

    /**
     * @return String return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return String return the tags
     */
    public String getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(String tags) {
        this.tags = tags;
    }
}
