package com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories;

import android.util.Log;

import com.cinema.avans.cinemaapp.backEnd.DatabaseManager;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatRowInstance;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Showing;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.HallInstance;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class HallInstanceRepository {

    private DatabaseManager databaseManager;

    public HallInstanceRepository(DatabaseManager databaseManager) {

        this.databaseManager = databaseManager;

    }

    // When a hall instance is added to the database:
    // - The HallInstance itself has to be added
    // - All of the SeatRowInstances have to be added
    // - All of the SeatInstances per SeatRowInstance have to be added
    public void createHallInstance(HallInstance hallInstance) {

        // Log
        Log.i("HallInstanceRepository", "Creating : " + hallInstance);

        // Add HallInstance and add generated id
        hallInstance.setHallInstanceId(databaseManager.createHallInstance(hallInstance));

        Log.i("HallInstanceRepository", "Created HallInstance: " + hallInstance);

        // Add all SeatRow instances
        for (SeatRowInstance seatRowInstance : hallInstance.getSeatRowInstances()) {
            new SeatRowInstanceRepository(databaseManager).createSeatRowInstance(seatRowInstance);

        }

    }

    public HallInstance getHallInstance(Showing showing) {

        HallInstance hallInstance = databaseManager.getHallInstance(showing.getHallInstance().getHallInstanceId());
        hallInstance.setSeatRowInstances(new SeatRowInstanceRepository(databaseManager).getSeatRowInstances(hallInstance));

        return hallInstance;

    }

}
