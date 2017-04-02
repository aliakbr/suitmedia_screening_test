package com.inkubatorit.test_application;

/**
 * Created by Ali-pc on 4/2/2017.
 */
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Ali-pc on 2017-02-25.
 */

public class PostCaller extends AsyncTask<String, Void, String> {
    private CompleteListener listener;

    public PostCaller(CompleteListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        String url = params[0];
        URL dest;
        try {
            dest = new URL(url);
            HttpURLConnection conn = (HttpsURLConnection) dest.openConnection();

//            conn.setDoInput(true);
//            conn.setDoOutput(true);
//            conn.setRequestProperty("Content-Type", "application/json");
//            conn.setRequestProperty("Accept", "application/json");
//            conn.setRequestMethod("GET");

//            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(conn.getOutputStream());
//            outputStreamWriter.write(json);
//            outputStreamWriter.close();

            StringBuilder sb = new StringBuilder();
            int HttpResult = conn.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                System.out.println("" + sb.toString());
            } else {
                System.out.println(conn.getResponseMessage());
            }
            return sb.toString();
        } catch ( Exception  e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        listener.onComplete(s);
    }

    public interface CompleteListener {
        void onComplete(String result);
    }
}
