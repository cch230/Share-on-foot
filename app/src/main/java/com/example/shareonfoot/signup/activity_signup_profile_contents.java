package com.example.shareonfoot.signup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shareonfoot.R;
import com.example.shareonfoot.activity_login;
import com.example.shareonfoot.home.activity_home;
import com.google.android.material.textfield.TextInputLayout;



import java.io.IOException;

import retrofit2.Call;

public class activity_signup_profile_contents extends AppCompatActivity {

    String userID, userPW, userNAME;
    Button joinBtn, joinBtn1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.layout_signup_profile_contents);
        joinBtn = findViewById(R.id.bt_join1);
        joinBtn1 = findViewById(R.id.bt_join2);
        userID = getIntent().getExtras().getString("userID");
        userPW = getIntent().getExtras().getString("userPW");
        userNAME = getIntent().getExtras().getString("nickname");
        Btnonclicker btnonclicker = new Btnonclicker();
        joinBtn.setOnClickListener(btnonclicker);
        joinBtn1.setOnClickListener(btnonclicker);


    }

    @Override
    public void onStop() {
        super.onStop();
        finish();
    }

    class Btnonclicker implements View.OnClickListener{
        @Override
        public void onClick(View v) {
                if(v.getId()== R.id.bt_join1){
                    // 회원가입 버튼 눌렀을 경우
                    SharedPreferences sharedPreferences=getSharedPreferences("pref",0);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString(userID,userID);
                    editor.putString(userID+"PW",userPW);
                    editor.putString(userID+"NAME",userNAME);
                    editor.putString("login",userID);
                    editor.commit();
                    try {
                        Toast.makeText(activity_signup_profile_contents.this,"회원가입이 완료되었습니다!",Toast.LENGTH_SHORT).show();
                        finishAffinity();
                        Intent intent = new Intent(getApplicationContext(), activity_home.class); //로그인 자동으로
                        startActivity(intent);
                        finish();
                    }catch (Exception e) { Log.i("Exception",e.toString());}
                }
                else if(v.getId()== R.id.bt_join2){
                    SharedPreferences sharedPreferences=getSharedPreferences("pref",0);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString(userID,userID);
                    editor.putString(userID+"PW",userPW);
                    editor.putString(userID+"NAME",userNAME);
                    editor.putString("login",userID);
                    editor.commit();
                    try {
                        Toast.makeText(activity_signup_profile_contents.this,"회원가입이 완료되었습니다!",Toast.LENGTH_SHORT).show();
                        finishAffinity();
                        Intent intent = new Intent(getApplicationContext(), activity_home.class); //로그인 자동으로
                        startActivity(intent);
                        finish();
                    }catch (Exception e) {}
                }
            }

    }

}
