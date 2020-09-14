package com.mboukhlouf.passwordmanager.application.common.dtos;

public class UserDto {
    private Integer id;
    private String username;

    public UserDto() {
    }
    
    public UserDto(Integer id, String username) {
        this.id = id;
        this.username = username;
    }
     
    /**
     * @return Integer return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
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
}
