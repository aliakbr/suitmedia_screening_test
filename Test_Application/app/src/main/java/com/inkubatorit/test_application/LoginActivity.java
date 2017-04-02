package com.inkubatorit.test_application;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    public EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        input = (EditText)findViewById(R.id.name_input);
    }

    public void Next(View view){
        String username = input.getText().toString();
        Intent myIntent = new Intent(LoginActivity.this, Option_Activity.class);
//        myIntent.putExtra("username", username); //Optional parameters
        DataHolder.getInstance().setData(username, 0);
        LoginActivity.this.startActivity(myIntent);
    }
}
