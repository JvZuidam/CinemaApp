package com.cinema.avans.cinemaapp.frontEnd.domain.cinema;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class Movie implements Serializable {

    private int movieId;
    private String title;
    private String description;
    private String runtime;
    private String releaseDate;
    private double rating;
    private String imageUrl;
    private ArrayList<Showing> showings;

    public Movie() {

        this.movieId = 0;
        this.title = "";
        this.description = "";
        this.runtime = "";
        this.releaseDate = "";
        this.rating = 0.0;
        this.imageUrl = "";
        this.showings = new ArrayList<>();

    }

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
    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    public void setRating(String rating) {
        this.rating = Double.parseDouble(rating);
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void setShowings(ArrayList<Showing> showings) {
        this.showings = showings;
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
    public String getRuntime() {
        return runtime;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public Double getRating() {
        return rating;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public ArrayList<Showing> getShowing() {
        return showings;
    }

    public String toString() {
        return "MovieId: " + movieId + "\n" +
                "Title: " + title + "\n" +
                "Description: " + description + "\n" +
                "Runtime: " + runtime + "\n" +
                "ReleaseDate: " + releaseDate + "\n" +
                "Rating: " + rating + "\n" +
                "ImageUrl: " + imageUrl;
    }

}
