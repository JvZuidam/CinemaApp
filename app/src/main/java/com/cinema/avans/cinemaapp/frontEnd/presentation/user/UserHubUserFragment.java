package com.cinema.avans.cinemaapp.frontEnd.presentation.user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.domain.login.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserHubUserFragment extends Fragment {

    private User user;
    private TicketAdapter ticketAdapter;
    private ListView ticketListView;

    public UserHubUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_hub_user, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        ticketListView = view.findViewById(R.id.userHubUserTicketsListView);
        ticketAdapter = new TicketAdapter(getContext(), user.getTickets());
        ticketListView.setAdapter(ticketAdapter);

    }

    public void setUser(User user) {
        Log.i("UserHubUserFragment", "User gotten: " + user);
        this.user = user;
    }


}
