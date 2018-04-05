package com.cinema.avans.cinemaapp.frontEnd.presentation.manager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.NewMovieListener;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.RepositoryFactory;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Movie;
import com.cinema.avans.cinemaapp.frontEnd.logic.manager.MovieFactory;
import com.squareup.picasso.Picasso;

/**
 * Created by JanBelterman on 31 March 2018
 */

public class CreateMovieActivity extends AppCompatActivity implements NewMovieListener {

    private MovieFactory movieFactory;
    private Movie movie;
    private EditText movieTitleInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_movie);

        // Set up movie factory
        movieFactory = new MovieFactory(this);

        setUpViews();

        setUpButtons();

    }

    private void setUpViews() {

        movieTitleInput = findViewById(R.id.createMovieTitleInput);

    }

    private void setUpButtons() {

        Button searchButton = findViewById(R.id.createMovieSearchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                movieFactory.getNewMovie(movieTitleInput.getText().toString());

            }
        });

        Button saveButton = findViewById(R.id.createMovieSaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (movie == null) {
                    Toast.makeText(getApplicationContext(), R.string.chooseAMovieFirst, Toast.LENGTH_SHORT).show();

                } else {
                    // Add movie to database
                    RepositoryFactory repositoryFactory = new RepositoryFactory(getApplicationContext());
                    repositoryFactory.getMovieRepository().createMovie(movie);
                    Toast.makeText(getApplicationContext(), R.string.movieAdded, Toast.LENGTH_SHORT).show();
                    finish();

                }

            }
        });

    }

    @Override
    public void newApiMovie(Movie movie) {

        this.movie = movie;
        displayMovie();


    }

    private void displayMovie() {

        ImageView movieImage = findViewById(R.id.createMovieImage);
        Picasso.with(getApplicationContext()).load(movie.getImageUrl()).into(movieImage);
        TextView movieTitle = findViewById(R.id.createMovieTitleText);
        movieTitle.setText(movie.getTitle());

    }

}
