package com.cinema.avans.cinemaapp.frontEnd.dataAcces.connections;

import android.os.AsyncTask;
import android.util.Log;

import com.cinema.avans.cinemaapp.frontEnd.dataAcces.NewMovieListener;
import com.cinema.avans.cinemaapp.frontEnd.domain.Movie;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by JanBelterman on 29 March 2018
 */

public class MovieApiManager extends AsyncTask<String, Void, String> {
    private NewMovieListener newMovieListener;

    public MovieApiManager(NewMovieListener newMovieListener) {
        this.newMovieListener = newMovieListener;

    }

    @Override
    protected String doInBackground(String... strings) {

        Log.i("Api", "Sending api request");

        BufferedReader bufferedReader = null;
        String response = "";

        try {

            // Getting the url link from the parameters
            URL url = new URL(strings[0]);
            // Opening the connection
            URLConnection urlConnection = url.openConnection();

            // Defining the buffered reader
            bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            urlConnection.getInputStream()
                    ));

            // Defining the response string
            response = bufferedReader.readLine();

            // Declaring the string line
            String line;

            // Lopping through the buffered reader's lines
            while ((line = bufferedReader.readLine()) != null) {
                response += line; // Adding each line

            }

        } catch (Exception e) {
            // Print error and return a null value
            e.printStackTrace();
            return null;

        } finally { // In the end
            // Check if buffered reader is closed (null) otherwise close it
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();

                } catch (Exception e) {
                    // Print error and return a null value
                    e.printStackTrace();
                    return null;

                }

            }

        }

        Log.i("Api", "Response received");

        // Return the JSON response which is received
        return response;

    }

    // JSON parser method
    @Override
    public void onPostExecute(String response) {

        Log.i("Api", "Parsing new movie");

        try {

            // Get movies from api
            JSONObject movieJSON = new JSONObject(response);

            // Get the title
            String title = movieJSON.getString("Title");
            String description = movieJSON.getString("Plot");
            String imageUrl = movieJSON.getString("Poster");


            // Create a new movie
            Movie movie = new Movie();
            movie.setTitle(title);
            movie.setDescription(description);
            movie.setImageUrl(imageUrl);

            // Log data to check
            Log.i("Api", movie.toString());

            newMovieListener.newApiMovie(movie);

        } catch (Exception e) {
            // Print errors
            e.printStackTrace();

        }

    }


}
