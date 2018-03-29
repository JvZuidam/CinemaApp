package com.cinema.avans.cinemaapp.frontEnd.dataAcces;

import com.cinema.avans.cinemaapp.frontEnd.domain.Movie;
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

    public ArrayList<Showing> getShowings(Movie movie) {

        ArrayList<Showing> showings = databaseManager.getShowings(movie);

        for (Showing showing : showings) {

            showing.setMovie(movie);
            showing.setHallInstance(databaseManager.getHallInstance(showing.getHallInstance().getHallInstanceId()));

        }

        return showings;

    }

}
