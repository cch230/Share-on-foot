package com.example.shareonfoot.signup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shareonfoot.R;
import com.example.shareonfoot.activity_login;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

import retrofit2.Call;

public class activity_signup_profile_contents extends AppCompatActivity {

    String userID, userPW;
    EditText tv_profile_contents;
    Button joinBtn, joinBtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userID = getIntent().getExtras().getString("userID");
        userPW = getIntent().getExtras().getString("userPW");
        setContentView(R.layout.layout_signup_profile_contents);

        joinBtn = (Button) findViewById(R.id.bt_join);
        joinBtn1 = (Button) findViewById(R.id.bt_join1);
        SharedPreferences sharedPreferences=getSharedPreferences("pref",0);
        String text = sharedPreferences.getString("userID","");
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("userID",userID);
        editor.putString("userPW",userPW);
        editor.commit();
    }










    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_join: // 회원가입 버튼 눌렀을 경우
                String profile_contents = tv_profile_contents.getText().toString();

                try {
                    Toast.makeText(activity_signup_profile_contents.this,"회원가입이 완료되었습니다!",Toast.LENGTH_SHORT).show();
                    finishAffinity();
                    Intent intent = new Intent(getApplicationContext(), activity_login.class); //로그인 자동으로
                    startActivity(intent);
                    finish();
                }catch (Exception e) {}
                break;
            case  R.id.bt_join1:
                profile_contents = tv_profile_contents.getText().toString();

                try {
                    Toast.makeText(activity_signup_profile_contents.this,"회원가입이 완료되었습니다!",Toast.LENGTH_SHORT).show();
                    finishAffinity();
                    Intent intent = new Intent(getApplicationContext(), activity_login.class); //로그인 자동으로
                    startActivity(intent);
                    finish();
                }catch (Exception e) {}
                break;

        }
    }
}
