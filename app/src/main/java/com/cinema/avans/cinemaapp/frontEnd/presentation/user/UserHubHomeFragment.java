package com.cinema.avans.cinemaapp.frontEnd.presentation.user;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinema.avans.cinemaapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserHubHomeFragment extends Fragment {


    public UserHubHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_hub_home, container, false);
    }

}