package com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories;

import com.cinema.avans.cinemaapp.backEnd.DatabaseManager;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatInstance;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatRowInstance;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class SeatInstanceRepository {

    private DatabaseManager databaseManager;

    public SeatInstanceRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;

    }

    public void createSeatInstance(SeatInstance seatInstance) {

        databaseManager.createSeatInstance(seatInstance);

    }

    public ArrayList<SeatInstance> getSeatInstances(SeatRowInstance seatRowInstance) {

        ArrayList<SeatInstance> seatInstances = databaseManager.getSeatInstances(seatRowInstance);

        for (SeatInstance seatInstance : seatInstances) {

            //seatInstance.setValue(); // Get from super entity
            //seatInstance.setNr(); // Get from super entity
            seatInstance.setSeatRowInstance(seatRowInstance);

        }

        return seatInstances;

    }

}
