package com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories;

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

        // Add the seat row instance
        databaseManager.createSeatRowInstance(seatRowInstance);

    }

    public ArrayList<SeatRowInstance> getSeatRowInstances(HallInstance hallInstance) {

        ArrayList<SeatRowInstance> seatRowInstances = databaseManager.getSeatRowInstances(hallInstance);

        for (SeatRowInstance seatRowInstance : seatRowInstances) {
            seatRowInstance.setHallInstance(hallInstance);
            seatRowInstance.setRowNr(databaseManager.getSeatRow(seatRowInstance.getSeatRowId()));
            seatRowInstance.setSeatInstances(databaseManager.getSeatInstances(seatRowInstance));

        }

        return seatRowInstances;

    }

}