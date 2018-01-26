package com.example.istimbi.simplereader;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by istim on 21.01.2018.
 */

public class ConnectToServer extends AsyncTask<String, Void, String> {
    private String result;
    @Override
    protected String doInBackground(String... strings) {
        try {
            result = doGet(strings[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String doGet(String url)
            throws Exception {

        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        //add request header
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0" );
        connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setConnectTimeout(3000);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = bufferedReader.readLine()) != null) {
            response.append(inputLine);
        }
        bufferedReader.close();

//      print result
        Log.d("answer","Response string: " + response.toString());
        return response.toString();
    }

}
