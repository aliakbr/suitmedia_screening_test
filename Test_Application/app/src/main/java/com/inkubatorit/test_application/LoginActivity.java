package com.inkubatorit.test_application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    public EditText input;

    public boolean isPalindrome(String s){
        return s.equals(new StringBuffer(s).reverse().toString());
    }

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
        final Button btn_signup = (Button)findViewById(R.id.button_login);
        btn_signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String message;
//                btn_signup.setBackground(getResources().getDrawable(R.drawable.btn_signup_selected));
                String username = input.getText().toString();
                if (isPalindrome(input.getText().toString())){
                    message = "isPalindrome";
                }
                else{
                    message = "not Palindrome";
                }
                Toast.makeText(LoginActivity.this, "" + message,
                        Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(LoginActivity.this, Option_Activity.class);
                DataHolder.getInstance().setData(username, 0);
                LoginActivity.this.startActivity(myIntent);
            }
        });
    }
}
