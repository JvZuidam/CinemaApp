package com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories;

import com.cinema.avans.cinemaapp.backEnd.DatabaseManager;
import com.cinema.avans.cinemaapp.frontEnd.domain.login.Manager;

/**
 * Created by JanBelterman on 29 March 2018
 */

public class ManagerRepository {

    DatabaseManager databaseManager;

    public ManagerRepository(DatabaseManager databaseManager) {

        this.databaseManager = databaseManager;

    }

    public void createManager(Manager manager) {

        databaseManager.createManager(manager);

    }

    public Manager getManager(String username) {

        return databaseManager.getManager(username);

    }

}