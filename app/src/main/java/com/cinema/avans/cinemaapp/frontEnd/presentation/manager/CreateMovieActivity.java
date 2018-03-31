package com.cinema.avans.cinemaapp.frontEnd.presentation.manager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.backEnd.DatabaseManager;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.NewMovieListener;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories.RepositoryFactory;
import com.cinema.avans.cinemaapp.frontEnd.domain.Movie;
import com.cinema.avans.cinemaapp.frontEnd.logic.manager.MovieFactory;

/**
 * Created by JanBelterman on 31 March 2018
 */

public class CreateMovieActivity extends AppCompatActivity implements NewMovieListener {

    private MovieFactory movieFactory;
    private EditText movieTitleInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_movie);

        movieFactory = new MovieFactory(this);

        setUpViews();

        setUpButtons();

    }

    private void setUpViews() {

        movieTitleInput = findViewById(R.id.createMovieTitleInput);

    }

    private void setUpButtons() {

        Button saveButton = findViewById(R.id.createMovieSaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                movieFactory.getNewMovie(movieTitleInput.getText().toString());

            }
        });

    }

    @Override
    public void newApiMovie(Movie movie) {

        // Add movie to database
        RepositoryFactory repositoryFactory = new RepositoryFactory(getApplicationContext());
        repositoryFactory.getMovieRepository().createMovie(movie);

    }

}
