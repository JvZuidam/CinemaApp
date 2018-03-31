package com.cinema.avans.cinemaapp.frontEnd.domain.login;

/**
 * Created by JanBelterman on 28,March,2018
 */

public class User extends LogIn {

    public User() {

        super();

    }

    public int getId() {

        return 1;

    }

    @Override
    public String toString() {

        return "User, username: " + userId + ", password: " + password;

    }

}
