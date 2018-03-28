package com.cinema.avans.cinemaapp.frontEnd.dataAcces;

import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatRow;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class SeatRowRepository {

    DatabaseManager databaseManager;

    public SeatRowRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;

    }

    public ArrayList<SeatRow> getSeatRows(int hallId) {
        return databaseManager.getSeatRows(hallId);

    }

}
