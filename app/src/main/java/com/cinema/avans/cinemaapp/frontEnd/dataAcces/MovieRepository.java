package com.cinema.avans.cinemaapp.frontEnd.dataAcces;

import com.cinema.avans.cinemaapp.frontEnd.domain.Movie;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class MovieRepository {

    DatabaseManager databaseManager;

    public MovieRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;

    }

    public Movie getMovie(int movieId) {

        return databaseManager.getMovie(movieId);

    }

}
