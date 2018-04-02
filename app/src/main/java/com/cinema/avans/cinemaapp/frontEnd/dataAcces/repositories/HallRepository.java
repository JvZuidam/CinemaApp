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

        // Add Hall
        databaseManager.createHall(hall);

    }

    // Returns a single Hall from the database
    // All SeatRows and Seats within each SeatRow included
    public Hall getHall(int hallId) {

        Log.i("Database", "Asking database for Hall with hallId: " + hallId);
        return databaseManager.getHall(hallId);

    }

    // Returns all Halls from database
    public ArrayList<Hall> getAllHalls() {

        Log.i("HallRepository", "Asking database for all Halls");
        return databaseManager.getAllHalls();

    }

}
