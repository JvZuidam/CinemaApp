package com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories;

import android.util.Log;

import com.cinema.avans.cinemaapp.backEnd.DatabaseManager;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Hall;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Seat;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatRow;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class HallRepository {

    DatabaseManager databaseManager;

    public HallRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;

    }

    // Creates an entire Hall (SeatRows and Seats included)
    public void createHall(Hall hall) {
        databaseManager.createHall(hall);

        for (SeatRow seatRow : hall.getSeatRows()) {
            databaseManager.createSeatRow(seatRow);

            for (Seat seat : seatRow.getSeats()) {
                databaseManager.createSeat(seat);
                // Seats from SeatRow added
            }
            // SeatRow with seats added
        }
        // Hall with SeatRows added
    }

    // Returns a single Hall from the database
    public Hall getHall(int hallId) {

        Hall hall = databaseManager.getHall(hallId);
        // Also get seatRows! with seat row repository, otherwise seats will nog be added
        hall.setSeatRows(new SeatRowRepository(databaseManager).getSeatRows(hall));

        return hall;

    }

    // Returns all Halls from database
    public ArrayList<Hall> getAllHalls() {

        ArrayList<Hall> halls = databaseManager.getAllHalls();

        for (Hall hall : halls) {
            hall.setSeatRows(databaseManager.getSeatRows(hall.getHallId()));

            for (SeatRow seatRow : hall.getSeatRows()) {
                seatRow.setSeats(databaseManager.getSeats(seatRow.getRowId()));
                Log.i("HallRepository", "Amount of seats in row: " + seatRow.getSeats().size());

            }

        }

        return halls;

    }

}
