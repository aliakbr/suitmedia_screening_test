package com.inkubatorit.test_application;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class GuestActivity extends AppCompatActivity {
    private String message = "feature key"; //default
    private String wait_message = "data being fetched, please wait";
    private ArrayList<String> guest;
    private ArrayList<String> birthday;
    private boolean data_fethced = false;
    JSONArray data;
    private SwipeRefreshLayout swipeContainer;


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
                swipeContainer.setRefreshing(false);
            }
        }).execute(url);
    }

    //check prime with 6k-1 and 6k+1 algorithm
    // for all number except 2 and 3 (and dividable number), prime number is in the form of 6k-1 or 6k+1
    public boolean isPrime(int n){
        for (int i = 2; i < n/2; i++){
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }

    public void checkDateOfBirth(String date){
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date birth = formatter1.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(birth);
            int someDate = cal.get(Calendar.DAY_OF_MONTH);
            int someMonth = cal.get(Calendar.MONTH);
            if (someDate % 2 == 0){
                message = "blackberry";
            }
            if (someDate % 3 == 0){
                message = "android";
            }
            if (someDate % 3 == 0 && someDate % 2 == 0){
                message = "iOS";
            }

            if (isPrime(someMonth)){
                message += " and Prime Month";
            }
            else{
                message += " and Not Prime Month";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        GridView gridview = (GridView) findViewById(R.id.guest_grid_view);
        gridview.setAdapter(new GuestViewAdapter(GuestActivity.this));
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Action when refreshed
                try {
                    initializeData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

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

    public void goBack(View view){
        finish();
    }
}
