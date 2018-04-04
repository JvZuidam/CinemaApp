package com.cinema.avans.cinemaapp.frontEnd.presentation.user;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.RepositoryFactory;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Movie;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserHubMoviesFragment extends Fragment {

    private RepositoryFactory repositoryFactory;
    private MovieAdapter movieAdapter;

    public UserHubMoviesFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_hub_movies, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        // Create repository factory and get movies
        repositoryFactory = new RepositoryFactory(view.getContext());
        ArrayList<Movie> movies = repositoryFactory.getMovieRepository().getAllMovieWithoutTheirShowings();
        Log.i("MovieListActivity", "Movie 1, Title: " + movies.get(0).getTitle());

        ListView movieListView = getView().findViewById(R.id.userHubMoviesMovieListView);
        movieAdapter = new MovieAdapter(view.getContext(), movies);
        movieListView.setAdapter(movieAdapter);
        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(view.getContext(), MovieDetailedActivity.class);
                intent.putExtra("MOVIE", movieAdapter.getItem(i));
                startActivity(intent);

            }
        });

    }

}
