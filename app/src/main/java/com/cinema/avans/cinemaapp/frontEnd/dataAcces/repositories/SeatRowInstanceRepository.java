package com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories;

import android.util.Log;

import com.cinema.avans.cinemaapp.backEnd.DatabaseManager;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.HallInstance;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatInstance;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatRowInstance;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class SeatRowInstanceRepository {

    private DatabaseManager databaseManager;

    public SeatRowInstanceRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;

    }

    public void createSeatRowInstance(SeatRowInstance seatRowInstance) {

        // Log
        Log.i("SeatRowInstanceRepo", "Creating: " + seatRowInstance);

        // Add the seat row instance
        seatRowInstance.setSeatRowInstanceId(databaseManager.createSeatRowInstanceAndGetGeneratedId(seatRowInstance));

        // Add the SeatInstances within the SeatRow
        for (SeatInstance seatInstance : seatRowInstance.getSeatInstances()) {
            new SeatInstanceRepository(databaseManager).createSeatInstance(seatInstance);

        }

    }

    public ArrayList<SeatRowInstance> getSeatRowInstances(HallInstance hallInstance) {

        ArrayList<SeatRowInstance> seatRowInstances = databaseManager.getSeatRowInstances(hallInstance);

        for (SeatRowInstance seatRowInstance : seatRowInstances) {
            seatRowInstance.setHallInstance(hallInstance);
            seatRowInstance.setSeatInstances(databaseManager.getSeatInstances(seatRowInstance));

        }

        return seatRowInstances;

    }

}
