package com.cinema.avans.cinemaapp.frontEnd.dataAcces;

import com.cinema.avans.cinemaapp.frontEnd.domain.Movie;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class MovieRepository implements NewMovieListener {

    private DatabaseManager databaseManager;
    private NewMovieListener newMovieListener;

    public MovieRepository(DatabaseManager databaseManager, NewMovieListener newMovieListener) {
        this.databaseManager = databaseManager;
        this.newMovieListener = newMovieListener;

    }

    public Movie getMovie(int movieId) {

        return databaseManager.getMovie(movieId);

    }

    public void getNewMovie(String title) {

        MovieApiManager movieApiManager = new MovieApiManager(this);
        movieApiManager.execute("http://www.omdbapi.com/?apikey=556fd57f&plot=full&t=" + title);

    }

    public void newApiMovie(Movie movie) {

        newMovieListener.newApiMovie(movie);

    }

}
