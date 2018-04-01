package com.cinema.avans.cinemaapp.frontEnd.presentation.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories.RepositoryFactory;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Hall;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 01 April 2018
 */

public class CreateShowingSelectHallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_showing_select_hall);

        // Get movies
        ArrayList<Hall> halls = new RepositoryFactory(getApplicationContext()).getHallRepository().getAllHalls();

        // Display movies
        final HallAdapter hallAdapter = new HallAdapter(getApplicationContext(), halls);
        ListView hallListView = findViewById(R.id.createShowingSelectHallListView);
        hallListView.setAdapter(hallAdapter);

        // Set click listener
        hallListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Hall hall = hallAdapter.getItem(i);
                Intent intent = new Intent();
                intent.putExtra("HALL", hall);
                setResult(3, intent);
                finish();

            }
        });

    }

}
