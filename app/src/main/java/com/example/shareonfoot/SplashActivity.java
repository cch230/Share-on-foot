package com.example.shareonfoot;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.example.shareonfoot.home.activity_home;
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences pref= getSharedPreferences("pref",0);
        String loginecheck = pref.getString("userID","");

        if(!loginecheck.equals(""))
            startActivity(new Intent(this, activity_home.class));
        else
            startActivity(new Intent(this,activity_login.class));
        finish();
    }
}