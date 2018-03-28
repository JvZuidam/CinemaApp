package com.cinema.avans.cinemaapp.frontEnd.dataAcces;

import com.cinema.avans.cinemaapp.frontEnd.domain.Showing;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.HallInstance;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class HallInstanceRepository {

    private DatabaseManager databaseManager;

    public HallInstanceRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;

    }

    public HallInstance getHallInstance(Showing showing) {

        HallInstance hallInstance = databaseManager.getHallInstance(showing.getHallInstance().getHallInstanceId());
        hallInstance.setRows(databaseManager.getSeatRowInstances(hallInstance));

        return hallInstance;

    }

}
