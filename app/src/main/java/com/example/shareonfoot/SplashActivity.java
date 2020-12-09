package com.example.shareonfoot;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.shareonfoot.home.activity_home;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;

public class SplashActivity extends AppCompatActivity {
    static {
        if (!OpenCVLoader.initDebug()) {
            Log.wtf("TAG", "OpenCV failed to load!");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences pref= getSharedPreferences("pref",0);
        mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);

        SharedPreferences.Editor editor=pref.edit();
        String login = pref.getString("login","");

        if(!login.equals(""))
            startActivity(new Intent(this, activity_home.class));
        else
            startActivity(new Intent(this,activity_login.class));
        finish();

    }
    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i("OpenCV", "OpenCV loaded successfully");
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };
}