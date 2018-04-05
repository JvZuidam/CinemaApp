package com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories;

import com.cinema.avans.cinemaapp.backEnd.DatabaseManager;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Movie;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class MovieRepository {

    private DatabaseManager databaseManager;

    public MovieRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;

    }

    public void createMovie(Movie movie) {

        databaseManager.createMovie(movie);

    }

    public ArrayList<Movie> getAllMovieWithoutTheirShowings() {

        // Get movies
        return databaseManager.getAllMovies();

    }

    Movie getMovie(int movieId) {
        return databaseManager.getMovie(movieId);

    }

}
