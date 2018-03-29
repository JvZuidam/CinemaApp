package com.cinema.avans.cinemaapp.frontEnd.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.DatabaseManager;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.MovieRepository;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.NewMovieListener;
import com.cinema.avans.cinemaapp.frontEnd.domain.Movie;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NewMovieListener {

    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Movie> movies = new ArrayList<>();

        MovieRepository movieRepository = new MovieRepository(
                new DatabaseManager(getApplicationContext(), "Cinema", null, 1)
                ,this);
        movieRepository.getNewMovie("war");
        movieRepository.getNewMovie("time");
        movieRepository.getNewMovie("bright");

        ListView movieListView = findViewById(R.id.movieListView);
        movieAdapter = new MovieAdapter(getApplicationContext(), movies);
        movieListView.setAdapter(movieAdapter);

    }

    // Test only normally manager class
    @Override
    public void newApiMovie(Movie movie) {

        movieAdapter.add(movie);
        movieAdapter.notifyDataSetChanged();

    }

}
