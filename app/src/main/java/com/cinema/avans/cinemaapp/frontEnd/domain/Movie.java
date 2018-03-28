package com.cinema.avans.cinemaapp.frontEnd.domain;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class Movie {

    private int movieId; // Done
    private String title; // Done
    private String description; // Done

    // SETTERS
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    // GETTERS
    public int getMovieId() {
        return movieId;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }

}
