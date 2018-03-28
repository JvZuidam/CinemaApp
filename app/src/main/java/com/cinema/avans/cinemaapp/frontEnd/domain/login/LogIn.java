package com.cinema.avans.cinemaapp.frontEnd.domain.login;

/**
 * Created by JanBelterman on 28,March,2018
 */

public abstract class LogIn {

    private String userId;
    private String password;

    // SETTERS
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // GETTERS
    public String getUserId() {
        return userId;
    }
    public String getPassword() {
        return password;
    }

}
