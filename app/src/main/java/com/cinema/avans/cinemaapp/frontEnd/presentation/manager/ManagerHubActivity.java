package com.cinema.avans.cinemaapp.frontEnd.presentation.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cinema.avans.cinemaapp.R;

/**
 * Created by JanBelterman on 31 March 2018
 */

public class ManagerHubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_hub);

        setUpButtons();

    }

    private void setUpButtons() {

        Button movieButton = findViewById(R.id.mHubCreateMovieButton);
        movieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ManagerHubActivity.this, CreateMovieActivity.class);
                startActivity(intent);

            }
        });

        Button showingButton = findViewById(R.id.mHubCreateShowingButton);
        showingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ManagerHubActivity.this, CreateShowingActivity.class);
                startActivity(intent);

            }
        });

    }

}
