package com.cinema.avans.cinemaapp.frontEnd.dataAcces;

import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Hall;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class HallRepository {

    DatabaseManager databaseManager;

    public HallRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;

    }

    public Hall getHall(int hallId) {
        return databaseManager.getHall(hallId);

    }

}
