package com.example.shareonfoot;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class activity_profile extends AppCompatActivity {


    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_profile);
        Button bt_logout = findViewById(R.id.bt_logout);
       /* imageButton = findViewById(R.id.camera);*/




    }
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_logout:
                startActivity(new Intent(activity_profile.this,activity_login.class));
                ActivityCompat.finishAffinity(activity_profile.this);
                break;
            /*case R.id.camera:
                startActivity(new Intent(activity_profile.this, camera.class));
                ActivityCompat.finishAffinity(activity_profile.this);*/
        }
    }
}