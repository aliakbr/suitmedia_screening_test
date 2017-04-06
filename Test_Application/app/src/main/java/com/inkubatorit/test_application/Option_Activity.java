package com.inkubatorit.test_application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Option_Activity extends AppCompatActivity {
    //To Pass intent response and request
    private static final int EVENT_FINISHED = 1;
    private static final int GUEST_FINISHED = 2;

    public String username;
    public String event_name;
    public String guest_name;
    Button event_button_View;
    Button guest_button_View;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_option_);
        event_button_View = (Button) findViewById(R.id.button_event);
        guest_button_View = (Button) findViewById(R.id.button_guest);

        if (DataHolder.getInstance().getData(0) != null) {
            username = DataHolder.getInstance().getData(0);
            TextView name_textView = (TextView) findViewById(R.id.user_name);
            name_textView.setText(username);
        }
        if (DataHolder.getInstance().getData(1) != null) {
            event_button_View.setText(DataHolder.getInstance().getData(1));
        }
        if (DataHolder.getInstance().getData(2) != null) {
            guest_button_View.setText(DataHolder.getInstance().getData(2));
        }

    }

    public void toEventOption(View view){
        Intent i = new Intent(this,EventListActivity.class);
        startActivityForResult(i, EVENT_FINISHED);
    }

    public void toGuestOption(View view){
        Intent i = new Intent(this,GuestActivity.class);
        startActivityForResult(i, GUEST_FINISHED);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (EVENT_FINISHED) : {
                if (resultCode == EventListActivity.RESULT_OK) {
                    event_name = data.getStringExtra("event_name");
                    System.out.println(event_name);
                    DataHolder.getInstance().setData(event_name, 1);
                    event_button_View.setText(event_name);
                }
                break;
            }
            case (GUEST_FINISHED) : {
                if (resultCode == GuestActivity.RESULT_OK) {
                    guest_name = data.getStringExtra("guest_name");
                    System.out.println(guest_name);
                    DataHolder.getInstance().setData(guest_name, 2);
                    guest_button_View.setText(guest_name);
                }
                break;
            }
        }
    }
}
