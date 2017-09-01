package com.example.iqbal.tandemapplication;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Iqbal on 9/1/17.
 */
public class LoadJSON extends AsyncTask<String, Void, Response> {

    private final FlightListener mFlightListener;

    public LoadJSON (FlightListener listener) {
        mFlightListener = listener;
    }

    public interface FlightListener {
        void onLoaded(List<Data> data);

        void onError();
    }

    @Override
    protected Response doInBackground(String... strings) {
        String stringResponse = null;

        try {
            stringResponse = loadJSON(strings[0]);
            Gson gson = new Gson();

            return gson.fromJson("{\"data\":" + stringResponse + "}", Response.class);
            // return gson.fromJson("{data:[{\"flight_number\": 1}]}", Response.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    protected void onPostExecute(Response response) {
        if (response != null) {
            mFlightListener.onLoaded(response.getData());
        } else {
            mFlightListener.onError();
        }
    }

    private String loadJSON(String jsonURL) throws IOException {
        URL url = new URL(jsonURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.setReadTimeout(15000);
        connection.setConnectTimeout(15000);
        connection.connect();

        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String lineRead;
        StringBuilder response = new StringBuilder();

        while ((lineRead = input.readLine()) != null) {
            response.append(lineRead);
        }
        input.close();

        Log.i("Response", response.toString());
        return response.toString();
    }
}
