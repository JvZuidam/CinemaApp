package com.cinema.avans.cinemaapp.frontEnd.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.CinemaHall;
import com.cinema.avans.cinemaapp.frontEnd.logic.SeatSelector;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, SeatSelectorActivity.class);
        intent.putExtra("SEAT_SELECTOR", new SeatSelector(new CinemaHall(), 2));
        startActivity(intent);

    }
}
