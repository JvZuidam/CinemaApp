package com.cinema.avans.cinemaapp.frontEnd.presentation.manager;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories.RepositoryFactory;
import com.cinema.avans.cinemaapp.frontEnd.domain.Date;
import com.cinema.avans.cinemaapp.frontEnd.domain.Movie;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Showing;
import com.cinema.avans.cinemaapp.frontEnd.presentation.user.MovieAdapter;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by JanBelterman on 31 March 2018
 */

public class CreateShowingActivity extends AppCompatActivity {

    private MovieAdapter movieAdapter;
    private TextView dateText;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private Showing showing;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_showing);

        // Create showing
        showing = new Showing();

        createMovieStuff();

        createDateStuff();

        createSaveButton();

    }

    private void createMovieStuff() {

        // Get movies
        ArrayList<Movie> movies = new RepositoryFactory(getApplicationContext()).getMovieRepository().getAllMovies();

        // Display movies
        movieAdapter = new MovieAdapter(getApplicationContext(), movies);
        ListView movieListView = findViewById(R.id.createShowingListView);
        movieListView.setAdapter(movieAdapter);

        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                showing.setMovie(movieAdapter.getItem(i));
                Toast.makeText(getApplicationContext(), "Movie selected: " + movieAdapter.getItem(i).getTitle(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void createDateStuff() {

        // Date stuff
        dateText = findViewById(R.id.createShowingDateText);
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        CreateShowingActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener,
                        year, month, day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                // Because months start at 0
                month = month + 1;

                // Create date
                date = new Date();
                date.setDay(day);
                date.setMonth(month);
                date.setYear(year);

                // Display date
                dateText.setText(day + "/" + month + "/" + year);

            }};

    }

    private void createSaveButton() {

        Button saveButton = findViewById(R.id.createShowingSaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showing.setDate(date);
                new RepositoryFactory(getApplicationContext()).getShowingRepository().createShowing(showing);
                Toast.makeText(getApplicationContext(), "Showing added", Toast.LENGTH_SHORT).show();

            }
        });

    }

}
