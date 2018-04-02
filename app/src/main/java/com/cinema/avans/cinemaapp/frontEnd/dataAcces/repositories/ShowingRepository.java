package com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories;

import android.util.Log;

import com.cinema.avans.cinemaapp.backEnd.DatabaseManager;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.HallInstance;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Movie;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Showing;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class ShowingRepository {

    private DatabaseManager databaseManager;

    public ShowingRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;

    }

    // Entire Showing object has to be stored that concludes all of the following things:
    // - Showing
    // - Complete HallInstance with its SeatRowInstances and SeatInstances
    public void createShowing(Showing showing) {

        Log.i("ShowingRepository", "Adding showing:" + "\n" + showing);

        showing.setShowingId(databaseManager.createShowing(showing).getShowingId());

        showing.getHallInstance().setShowing(showing);

        new HallInstanceRepository(databaseManager).createHallInstance(showing.getHallInstance());

    }

    public ArrayList<Showing> getShowings(Movie movie) {

        ArrayList<Showing> showings = databaseManager.getShowings(movie);

        for (Showing showing : showings) {

            showing.setMovie(movie);
            showing.setHallInstance(databaseManager.getHallInstance(showing.getHallInstance().getHallInstanceId()));

        }

        return showings;

    }

}
