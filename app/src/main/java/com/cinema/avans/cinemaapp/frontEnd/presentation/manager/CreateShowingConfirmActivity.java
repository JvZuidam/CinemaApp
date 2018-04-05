package com.cinema.avans.cinemaapp.frontEnd.presentation.manager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.RepositoryFactory;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Date;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Movie;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Hall;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Showing;
import com.cinema.avans.cinemaapp.frontEnd.logic.manager.HallInstanceFactory;
import com.cinema.avans.cinemaapp.frontEnd.logic.manager.HallRetriever;
import com.cinema.avans.cinemaapp.frontEnd.logic.manager.HallsRetrievedListener;
import com.cinema.avans.cinemaapp.frontEnd.logic.manager.ShowingAddedListener;
import com.cinema.avans.cinemaapp.frontEnd.logic.manager.ShowingAdder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by JanBelterman on 31 March 2018
 */

// Step 4: Checking and saving
public class CreateShowingConfirmActivity extends AppCompatActivity {

    private Showing showing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Setup activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_showing_confirm);

        setupActivity();

        displayShowing();

        createConfirmButton();

    }

    private void setupActivity() {

        Intent intent = getIntent();

        showing = (Showing) intent.getSerializableExtra("SHOWING");

    }

    private void displayShowing() {

        TextView movieTitle = findViewById(R.id.createShowingConfirmMovieTitle);
        movieTitle.setText(showing.getMovie().getTitle());

        ImageView movieImage = findViewById(R.id.createShowingConfirmMovieImage);
        Picasso.with(getApplicationContext()).load(showing.getMovie().getImageUrl()).into(movieImage);

        TextView hallNr = findViewById(R.id.createShowingConfirmHall);
        hallNr.setText(getString(R.string.hallNrWithSemiColon) + " " + showing.getHallInstance().getHall().getHallNr());

    }

    private void createConfirmButton() {

        Button confirmButton = findViewById(R.id.createShowingConfirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CreateShowingConfirmActivity.this, ManagerHubActivity.class);
                // Pass halls and movies so no exception when manager wants to add another showing??
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }
        });

    }

    @Override
    public void finish() {

        super.finish();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);

    }

}
