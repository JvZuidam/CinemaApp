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

public class CreateShowingActivity extends AppCompatActivity implements ShowingAddedListener, HallsRetrievedListener {

    // Showing
    private Showing showing;

    private RepositoryFactory repositoryFactory;
    private HallInstanceFactory hallInstanceFactory;
    private ShowingAdder showingAdder;
    private HallRetriever hallRetriever;

    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TimePickerDialog.OnTimeSetListener timeSetListener;
    private TextView dateText;
    private TextView timeText;
    private ProgressBar createShowingProgressbar;
    private ProgressBar selectHallProgressbar;

    // Time

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Setup activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_showing);

        setupActivity();

        // Create Movie and Hall select buttons and activities
        createMovieStuff();
        createHallStuff();

        // Create date and time dialogs
        createDateStuff();
        createTimeStuff();

        createSaveButton();

    }

    private void setupActivity() {

        // Create objects this activity uses
        this.repositoryFactory = new RepositoryFactory(getApplicationContext());
        this.hallInstanceFactory = new HallInstanceFactory();
        this.showingAdder = new ShowingAdder(this, repositoryFactory.getShowingRepository());
        this.hallRetriever = new HallRetriever(this, repositoryFactory.getHallRepository());

        // Create showing
        showing = new Showing();

        // Get TextViews for date and time
        dateText = findViewById(R.id.createShowingDateText);
        timeText = findViewById(R.id.createShowingTimeText);

        // Create and stop loaders
        createShowingProgressbar = findViewById(R.id.createShowingCreateShowingProgressbar);
        stopCreateShowingLoader();
        selectHallProgressbar = findViewById(R.id.createShowingSelectHallProgressbar);
        stopSelectHallLoader();

    }

    private void createMovieStuff() {

        Button movieSelectButton = findViewById(R.id.createShowingSelectMovieButton);
        movieSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CreateShowingActivity.this, CreateShowingSelectMovieActivity.class);
                startActivityForResult(intent, 2);

            }
        });

    }

    private void createHallStuff() {

        Button hallSelectButton = findViewById(R.id.createShowingSelectHallButton);
        hallSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startSelectHallLoader();

                hallRetriever.execute();

            }
        });

    }

    @Override
    public void hallsRetrieved(ArrayList<Hall> halls) {

        stopSelectHallLoader();

        // Start hall select activity and pass halls with intent
        Intent intent = new Intent(CreateShowingActivity.this, CreateShowingSelectHallActivity.class);
        intent.putExtra("HALLS", halls);
        startActivityForResult(intent, 3);

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
                showing.setHallInstance(hallInstanceFactory.createHallInstance((Hall) data.getSerializableExtra("HALL")));

            }

        }

        // Update the displayed values
        updateShowing();

    }

    // Updates all displayed information about current Showing
    private void updateShowing() {

        // Get views
        ImageView movieImage = findViewById(R.id.createShowingMovieImage);
        TextView movieTitle = findViewById(R.id.createShowingMovieTitle);
        TextView hallText = findViewById(R.id.createShowingHallText);

        // Update Movie info
        if (showing.getMovie() != null) {
            Picasso.with(getApplicationContext()).load(showing.getMovie().getImageUrl()).into(movieImage);
            movieTitle.setText(showing.getMovie().getTitle());

        }

        // Update Hall info
        if (showing.getHallInstance() != null) {
            hallText.setText(String.valueOf(showing.getHallInstance().getHall().getHallNr()));

        }

    }

    private void createDateStuff() {

        // Date stuff
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

                // Alter the date of the Showing
                Date date = showing.getDate();
                date.setDay(day);
                date.setMonth(month);
                date.setYear(year);

                // Display date
                dateText.setText(day + getString(R.string.slash) + month + getString(R.string.slash) + year);

            }};

    }

    private void createTimeStuff() {

        Button changeTimeButton = findViewById(R.id.createShowingSelectTimeButton);
        changeTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                int hours = cal.get(Calendar.HOUR_OF_DAY);
                int minutes = cal.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        CreateShowingActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        timeSetListener,
                        hours,
                        minutes,
                        true);

                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.show();

            }
        });

        timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                // Alter the time of the Showing
                Date date = showing.getDate();
                date.setHours(hour);
                date.setMinutes(minute);

                // Display time
                // If minutes is < 10 than add an extra 0
                if (minute < 10) {
                    timeText.setText(hour + " : 0" + minute);
                } else {
                    timeText.setText(hour + " : " + minute);
                }

            }
        };

    }

    private void createSaveButton() {

        Button saveButton = findViewById(R.id.createShowingSaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Check for mistakes from user
                if (!validateUserInput()) {
                    return;

                }

                // Start loader
                startCreateShowingLoader();

                showingAdder.execute(showing);

            }
        });

    }

    private boolean validateUserInput() {

        // Check if user selected a movie
        if (showing.getMovie() == null) {
            Toast.makeText(getApplicationContext(), R.string.selectMovieFirst, Toast.LENGTH_SHORT).show();

        }
        // Check if user selected a hall
        else if (showing.getHallInstance() == null) {
            Toast.makeText(getApplicationContext(), R.string.selectAHallFirst, Toast.LENGTH_SHORT).show();

        }
        // Check if user selected a date
        else if (dateText.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), R.string.selectDateFirst, Toast.LENGTH_SHORT).show();

        }
        // Check if user selected a time
        else if (timeText.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), R.string.selectTimeFirst, Toast.LENGTH_SHORT).show();

        }
        // If none of the above were true
        else {
            return true;

        }
        // If a single one of the above was true
        return false;

    }

    @Override
    public void showingAdded() {

        // Stop loader
        stopCreateShowingLoader();

        // Display message and finish activity
        Toast.makeText(getApplicationContext(), R.string.showingAdded, Toast.LENGTH_SHORT).show();
        finish();

    }

    private void startSelectHallLoader() {

        selectHallProgressbar.setVisibility(View.VISIBLE);

    }
    private void stopSelectHallLoader() {

        selectHallProgressbar.setVisibility(View.GONE);

    }

    private void startCreateShowingLoader() {

        createShowingProgressbar.setVisibility(View.VISIBLE);

    }
    private void stopCreateShowingLoader() {

        createShowingProgressbar.setVisibility(View.GONE);

    }

}
