package com.cinema.avans.cinemaapp.frontEnd.domain.login;

import java.io.Serializable;

/**
 * Created by JanBelterman on 28,March,2018
 */

public abstract class LogIn implements Serializable {

    protected String userId;
    protected String password;

    public LogIn() {

        this.userId = "";
        this.password = "";

    }

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

    public String toString() {

        return "UserId: " + userId + ", password: " + password;

    }

    public abstract int getId();

}
