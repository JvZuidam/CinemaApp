package com.cinema.avans.cinemaapp.frontEnd.domain;

import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Showing;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class Movie implements Serializable {

    private int movieId; // Done
    private String title; // Done
    private String description; // Done
    private String imageUrl; // Done
    private ArrayList<Showing> showings; // Done

    public Movie() {

        this.movieId = 0;
        this.title = "";
        this.description = "";
        this.imageUrl = "";
        this.showings = new ArrayList<>();

    }

    // SETTERS
    public void setShowings(ArrayList<Showing> showings) {
        this.showings = showings;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
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
    public ArrayList<Showing> getShowing() {
        return showings;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public int getMovieId() {
        return movieId;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }

    public String toString() {
        return "MovieId: " + movieId + ", title: " + title + ", imageUrl: " + imageUrl;
    }

}
