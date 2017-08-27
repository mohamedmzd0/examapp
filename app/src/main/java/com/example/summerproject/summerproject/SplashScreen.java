package com.example.summerproject.summerproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        /// this is splash screen used to only preview app logo
        // this app has no action bar implemented in themes xml
        new Handler().postDelayed(new Runnable() { //delay for 1000 millisecond
            @Override
            public void run() {
               ///this method performs after 1000 millisecond
                startActivity(new Intent(getApplicationContext(),SelectUserActivity.class)); //launch select user activity
                finish(); //close this activity to avoid display it with (back) button
            }
        },1000) ;
    }
}
