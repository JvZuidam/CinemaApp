package com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories;

import android.util.Log;
import android.widget.Toast;

import com.cinema.avans.cinemaapp.backEnd.DatabaseManager;
import com.cinema.avans.cinemaapp.frontEnd.domain.login.LogIn;

/**
 * Created by JanBelterman on 29 March 2018
 */

public class LogInRepository {

    private DatabaseManager databaseManager;

    public LogInRepository(DatabaseManager databaseManager) {

        this.databaseManager = databaseManager;

    }

    public LogIn getLogIn(String username) {

        LogIn login = null;

        Log.i("LogInRepository", "Asking database for user and manager for a given userId");

        // First look for user
        login = databaseManager.getUser(username);

        if (login != null) {
            return login;

        }
        // Than look for manager
        login = databaseManager.getManager(username);

        Log.i("LogInRepository", "LogInFound: " + login.toString());

        return login;

    }

}
