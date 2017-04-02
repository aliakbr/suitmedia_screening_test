package com.inkubatorit.test_application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.inkubatorit.test_application.PostCaller;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.inkubatorit.test_application.JSONParser.json;

public class GuestActivity extends AppCompatActivity {
    private String message = "feature key"; //default
    private String wait_message = "data being fetched, please wait";
    private ArrayList<String> guest;
    private ArrayList<String> birthday;
    private boolean data_fethced = false;
    JSONArray data;
    public void initializeData() throws IOException {
        guest = new ArrayList<String>();
        birthday = new ArrayList<String>();
        String url = "https://dry-sierra-6832.herokuapp.com/api/people";
        new PostCaller(new PostCaller.CompleteListener() {
            @Override
            public void onComplete(String result) {
                try {
                    data = new JSONArray(result);
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject jObj = data.getJSONObject(i);
                        guest.add(jObj.getString("name"));
                        birthday.add(jObj.getString("birthdate"));
                    }
                    data_fethced = true;
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).execute(url);
    }

    public void checkDateOfBirth(String date){
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date birth = formatter1.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(birth);
            int someDate = cal.get(Calendar.DAY_OF_MONTH);
            if (someDate % 2 == 0){
                message = "blackberry";
            }
            if (someDate % 3 == 0){
                message = "android";
            }
            if (someDate % 3 == 0 && someDate % 2 == 0){
                message = "iOS";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        try {
            initializeData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GridView gridview = (GridView) findViewById(R.id.guest_grid_view);
        gridview.setAdapter(new GuestViewAdapter(GuestActivity.this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if (data_fethced) {
                    checkDateOfBirth(birthday.get(position));

                    Toast.makeText(GuestActivity.this, "" + message,
                            Toast.LENGTH_SHORT).show();
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("guest_name", guest.get(position));
                    setResult(GuestActivity.RESULT_OK, resultIntent);
                    finish();
                }
                else{
                    Toast.makeText(GuestActivity.this, "" + wait_message,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}