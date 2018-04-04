package com.cinema.avans.cinemaapp.frontEnd.presentation.user;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.domain.login.User;

/**
 * Created by JanBelterman on 04 April 2018
 */

public class UserHubActivity extends AppCompatActivity {

    private User user;

    private BottomNavigationView navBar;
    private FrameLayout frameLayout;

    private UserHubHomeFragment userHubHomeFragment;
    private UserHubMoviesFragment userHubMoviesFragment;
    private UserHubUserFragment userHubUserFragment;

    @Override
    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_user_hub);

        user = (User) getIntent().getSerializableExtra("USER");

        navBar = findViewById(R.id.userHubNavBar);
        frameLayout = findViewById(R.id.userHubFrame);

        userHubHomeFragment = new UserHubHomeFragment();
        userHubMoviesFragment = new UserHubMoviesFragment();
        userHubUserFragment = new UserHubUserFragment();

        setFragment(userHubHomeFragment);

        navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.navHome:
                        setFragment(userHubHomeFragment);
                        return true;

                    case R.id.navMovies:
                        setFragment(userHubMoviesFragment);
                        return true;

                    case R.id.navUser:
                        setFragment(userHubUserFragment);
                        return true;

                    default:
                        return false;

                }

            }
        });

    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.userHubFrame, fragment);
        fragmentTransaction.commit();

    }

}
