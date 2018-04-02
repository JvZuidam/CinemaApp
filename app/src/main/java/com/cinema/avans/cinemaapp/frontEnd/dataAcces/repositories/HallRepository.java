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

        Log.i("HallRepository", "Creating hall:\n" + hall);

        // Add Hall
        databaseManager.createHall(hall);

        // Also add the SeatRows within the Hall
        for (SeatRow seatRow : hall.getSeatRows()) {
            new SeatRowRepository(databaseManager).createSeatRow(seatRow);

        }

    }

    // Returns a single Hall from the database
    // All SeatRows and Seats within each SeatRow included
    public Hall getHall(int hallNr) {

        Log.i("Database", "Asking database for Hall with hallNr: " + hallNr);

        Hall hall = databaseManager.getHall(hallNr);

        hall.setSeatRows(new SeatRowRepository(databaseManager).getSeatRows(hall));

        return hall;

    }

    // Returns all Halls from database
    public ArrayList<Hall> getAllHalls() {

        Log.i("HallRepository", "Asking database for all Halls");

        // Halls without SeatRows and Seats
        ArrayList<Hall> halls = databaseManager.getAllHalls();

        // Getting the SeatRows and Seats
        for (Hall hall : halls) {
            hall.setSeatRows(new SeatRowRepository(databaseManager).getSeatRows(hall));

        }

        return halls;

    }

}
