package com.example.shareonfoot.signup;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import retrofit2.Call;

import static java.net.URLEncoder.encode;

public class activity_signup_next extends AppCompatActivity {

    String userID, userPW;
    static String receiveMsg;
    EditText et_nickname, et_gender, et_birth;
    Button joinBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userID = getIntent().getExtras().getString("userID");
        userPW = getIntent().getExtras().getString("userPW");

        BtnOnClickListener onClickListener = new BtnOnClickListener();
        setContentView(R.layout.layout_signup_next);
        Button btnSignup = findViewById(R.id.bt_join);
        btnSignup.setOnClickListener(onClickListener);

        final TextInputLayout input_nickname = (TextInputLayout) findViewById(R.id.input_nickname);
        input_nickname.setCounterEnabled(true);
        input_nickname.setCounterMaxLength(50);
        input_nickname.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                if (text.length() == 0) {
                    input_nickname.setError("이름을 입력해야 합니다.");
                    input_nickname.setErrorEnabled(true);
                } else {
                    input_nickname.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        final TextInputLayout input_gender = (TextInputLayout) findViewById(R.id.input_gender);
        //input_gender.setCounterEnabled(true);
        //input_gender.setCounterMaxLength(10);
        input_gender.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                if (text.length() == 0) {
                    input_gender.setError("성별을 입력해야 합니다.");
                    input_gender.setErrorEnabled(true);
                } else {
                    input_gender.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        final TextInputLayout input_birth = (TextInputLayout) findViewById(R.id.input_birth);
        //input_birth.setCounterEnabled(true);
        //input_birth.setCounterMaxLength(45);
        input_birth.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                if (text.length() == 0) {
                    input_birth.setError("생일을 입력해야 합니다.");
                    input_birth.setErrorEnabled(true);
                } else {
                    input_birth.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        et_nickname = (EditText) findViewById(R.id.et_nickname);
        et_gender = (EditText) findViewById(R.id.et_gender);
        et_birth = (EditText) findViewById(R.id.et_birth);


        joinBtn = (Button) findViewById(R.id.bt_join);
    }

    class BtnOnClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.bt_join: // 회원가입 버튼 눌렀을 경우
                    String id = userID;
                    String pw = userPW;
                    String nickname = et_nickname.getText().toString();
                    String gender = et_gender.getText().toString();
                    String birth = et_birth.getText().toString();
                    if (nickname.length() == 0) {
                        Toast.makeText(activity_signup_next.this, "이름은 필수 입력사항입니다.", Toast.LENGTH_SHORT).show();
                    } else {

                        try {
                            // DB에 회원정보 저장
                            String result = new InsertTask().execute(id, pw, nickname, gender, birth).get();

                            if(result.equals("ok")) {
                                Toast.makeText(activity_signup_next.this, "회원 가입 완료", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), activity_signup_profile_contents.class);
                                intent.putExtra("userID", userID);
                                intent.putExtra("userPW", userPW);
                                intent.putExtra("nickname", userPW);
                                startActivity(intent);
                                finish();
                            }
                        } catch (Exception e) {
                        }
                    }
                    break;
            }
        }
    }

    class InsertTask extends AsyncTask<String, Void, String> {

        String sendMsg, receiveMsg;

        public String ip = "49.50.172.215:8080";
        String serverIp = "http://" + ip + "/shareonfoot/insertMember.jsp";    // 서버에 위치에 jsp 파일 저장되어 있어야 함

        @Override
        protected String doInBackground(String... strings) {

            try {
                String str;

                URL url = new URL(serverIp);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id=" + strings[0] + "&password=" + strings[1] + "&nickname=" + strings[2] + "&gender=" + strings[3] + "&birth=" + strings[4];
                osw.write(sendMsg);
                osw.flush();
                if (conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();

                } else {
                    Log.i("통신 결과", conn.getResponseCode() + "에러");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return receiveMsg;
        }
    }
}
