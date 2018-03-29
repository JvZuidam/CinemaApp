package com.cinema.avans.cinemaapp.frontEnd.dataAcces;

import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Hall;
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

    public ArrayList<SeatRow> getSeatRows(Hall hall) {

        ArrayList<SeatRow> seatRows = databaseManager.getSeatRows(hall.getHallId());

        for (SeatRow seatRow : seatRows) {

            seatRow.setSeats(databaseManager.getSeats(seatRow.getRowId()));

        }

        return seatRows;

    }

}
