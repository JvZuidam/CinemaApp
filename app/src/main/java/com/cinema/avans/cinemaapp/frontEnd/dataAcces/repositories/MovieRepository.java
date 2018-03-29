package com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories;

import com.cinema.avans.cinemaapp.backEnd.DatabaseManager;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.connections.MovieApiManager;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.NewMovieListener;
import com.cinema.avans.cinemaapp.frontEnd.domain.Movie;

import java.util.ArrayList;

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

    public void createMovie(Movie movie) {

        // Create the movie
        databaseManager.createMovie(movie);

    }

    public ArrayList<Movie> getAllMovies() {

        ArrayList<Movie> movies =  databaseManager.getAllMovies();

        for (Movie movie : movies) {

            movie.setShowings(databaseManager.getShowings(movie));

        }

        return movies;

    }

    public Movie getMovie(int movieId) {

        // Get movie from database
        Movie movie = databaseManager.getMovie(movieId);
        // Get showings of the movie from database
        movie.setShowings(new ShowingRepository(databaseManager).getShowings(movie));
        // Return complete movie
        return movie;

    }

    public void getNewMovie(String title) {

        MovieApiManager movieApiManager = new MovieApiManager(this);
        movieApiManager.execute("http://www.omdbapi.com/?apikey=556fd57f&plot=full&t=" + title);

    }

    public void newApiMovie(Movie movie) {

        newMovieListener.newApiMovie(movie);

    }

}
