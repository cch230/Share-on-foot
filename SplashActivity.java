package com.example.shareonfoot;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.shareonfoot.HTTP.Session.preference.CookieSharedPreferences;
import com.example.shareonfoot.HTTP.Session.preference.MySharedPreferences;
import com.example.shareonfoot.home.activity_home;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MySharedPreferences pref = MySharedPreferences.getInstanceOf(getApplicationContext());
        String loginedID = pref.getUserID();

        if("".equals(loginedID))
            startActivity(new Intent(this,activity_login.class));
        else
            startActivity(new Intent(this, activity_home.class));
        finish();
    }
}