package com.cinema.avans.cinemaapp.frontEnd.presentation.manager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories.RepositoryFactory;
import com.cinema.avans.cinemaapp.frontEnd.domain.Date;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Movie;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Hall;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Showing;
import com.cinema.avans.cinemaapp.frontEnd.logic.manager.HallInstanceFactory;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

/**
 * Created by JanBelterman on 31 March 2018
 */

public class CreateShowingActivity extends AppCompatActivity {

    private RepositoryFactory repositoryFactory;
    private HallInstanceFactory hallInstanceFactory;

    // Showing
    private Showing showing;

    // Movie
    private ImageView movieImage;
    private TextView movieTitle;
    private Button movieSelectButton;

    // Hall
    private TextView hallText;
    private Button hallSelectButton;

    // Date
    private TextView dateText;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    // Time

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_showing);

        this.repositoryFactory = new RepositoryFactory(getApplicationContext());
        this.hallInstanceFactory = new HallInstanceFactory();

        // Create showing
        showing = new Showing();

        createMovieStuff();
        createHallStuff();
        createDateStuff();

        showing.setMovie(repositoryFactory.getMovieRepository().getFirstMovie());
        updateShowing();

        createSaveButton();

    }

    private void createMovieStuff() {

        movieImage = findViewById(R.id.createShowingMovieImage);
        movieTitle = findViewById(R.id.createShowingMovieTitle);

        movieSelectButton = findViewById(R.id.createShowingSelectMovieButton);
        movieSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CreateShowingActivity.this, CreateShowingSelectMovieActivity.class);
                startActivityForResult(intent, 2);

            }
        });

    }

    private void createHallStuff() {

        hallText = findViewById(R.id.createShowingHallText);
        hallSelectButton = findViewById(R.id.createShowingSelectHallButton);
        hallSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CreateShowingActivity.this, CreateShowingSelectHallActivity.class);
                startActivityForResult(intent, 3);

            }
        });

    }

    // Catches the selected Movie and Hall
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // If the back button is pressed in the select activity
        if (data == null) {
            return;

        }

        // If the data contains a movie
        if (requestCode == 2) {
            if (data.getExtras() != null) {
                showing.setMovie((Movie) data.getSerializableExtra("MOVIE"));

            }
        }
        // If the data contains a hall
        else if (requestCode == 3) {
            if (data.getExtras() != null) {
                showing.setHallInstance(hallInstanceFactory.getHallInstance((Hall) data.getSerializableExtra("HALL")));

            }

        }

        // Update the displayed values
        updateShowing();

    }

    private void updateShowing() {

        if (showing.getMovie() != null) {
            Picasso.with(getApplicationContext()).load(showing.getMovie().getImageUrl()).into(movieImage);
            movieTitle.setText(showing.getMovie().getTitle());

        }

        if (showing.getHallInstance() != null) {
            hallText.setText(String.valueOf(showing.getHallInstance().getHallId()));

        }

    }

    private void createDateStuff() {

        // Date stuff
        dateText = findViewById(R.id.createShowingDateText);
        Button changeDateButton = findViewById(R.id.createShowingSelectDateButton);
        changeDateButton.setOnClickListener(new View.OnClickListener() {
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
                Date date = showing.getDate();
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

                // Create showing and finish
                repositoryFactory.getShowingRepository().createShowing(showing);
                Toast.makeText(getApplicationContext(), "Showing added", Toast.LENGTH_SHORT).show();
                finish();

            }
        });

    }

}
