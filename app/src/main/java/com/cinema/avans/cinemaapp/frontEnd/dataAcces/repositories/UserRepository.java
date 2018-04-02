package com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories;

import android.util.Log;

import com.cinema.avans.cinemaapp.backEnd.DatabaseManager;
import com.cinema.avans.cinemaapp.frontEnd.domain.login.User;

/**
 * Created by JanBelterman on 29 March 2018
 */

public class UserRepository {

    private DatabaseManager databaseManager;

    public UserRepository(DatabaseManager databaseManager) {

        this.databaseManager = databaseManager;

    }

    public void createUser(User user) {

        Log.i("UserRepository", "Creating user:" + "\n" + user);
        databaseManager.createUser(user);

    }

    public User getUser(String username) {

        return databaseManager.getUser(username);

    }

}
