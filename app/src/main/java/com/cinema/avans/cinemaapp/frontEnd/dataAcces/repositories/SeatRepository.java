package com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories;

import com.cinema.avans.cinemaapp.backEnd.DatabaseManager;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Seat;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatRow;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 29 March 2018
 */

public class SeatRepository {

    private DatabaseManager databaseManager;

    public SeatRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;

    }

    public void createSeat(Seat seat) {

        databaseManager.createSeat(seat);

    }

    public ArrayList<Seat> getSeats(SeatRow seatRow) {

        ArrayList<Seat> seats = databaseManager.getSeats(seatRow.getRowId());

        for (Seat seat : seats) {

            seat.setSeatRow(seatRow);

        }

        return seats;

    }

}
