package com.example.shareonfoot;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.shareonfoot.home.activity_home;
import com.example.shareonfoot.signup.activity_signup;
import com.example.shareonfoot.signup.activity_signup_next;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class activity_login extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    int count = 0;

    EditText userId, userPwd;
    Button loginBtn, joinBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        BtnOnClickListener onClickListener = new BtnOnClickListener();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_login);
        Button btnSignup = findViewById(R.id.bt_signup);
        btnSignup.setOnClickListener(onClickListener);
        Button btnLogin = findViewById(R.id.bt_login);
        btnLogin.setOnClickListener(onClickListener);

        userId = (EditText) findViewById(R.id.et_ID);
        userPwd = (EditText) findViewById(R.id.et_Password);
        loginBtn = (Button) findViewById(R.id.bt_login);
        joinBtn = (Button) findViewById(R.id.bt_join);
    }





    public class BtnOnClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {

                case R.id.bt_login: // 로그인 버튼 눌렀을 경우
                    String loginid = userId.getText().toString();
                    String loginpwd = userPwd.getText().toString();
                    SharedPreferences sharedPreferences=getSharedPreferences("pref",0);

                    if (loginid.length() == 0||loginpwd.length()==0) {
                        Toast.makeText(activity_login.this, "회원정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        try {
                            String result = new LoginTask().execute(loginid, loginpwd).get();
                            if (result.equals("true")) {

                                SharedPreferences pref = getSharedPreferences("pref", 0);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("login", loginid);
                                editor.commit();

                                Toast.makeText(activity_login.this, "로그인 성공!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(activity_login.this, activity_home.class);
                                startActivity(intent);
                                finish();
                            } else if (result.equals("false")) {
                                Toast.makeText(activity_login.this, "아이디나 비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show();
                                userId.setText("");
                                userPwd.setText("");
                            } else if (result.equals("noId")) {
                                // 원래 존재하지 않는 아이디입니다. 출력
                                Toast.makeText(activity_login.this, "아이디나 비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show();
                                userId.setText("");
                                userPwd.setText("");
                            }
                        } catch (Exception e) {
                        }
                    }
                        break;
//                    String loginid = userId.getText().toString();
//                    String loginpwd = userPwd.getText().toString();
//                    SharedPreferences sharedPreferences=getSharedPreferences("pref",0);
//                    if (loginid.length() == 0||loginpwd.length()==0) {
//                        Toast.makeText(activity_login.this, "회원정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
//                    }
//                    String userID = sharedPreferences.getString(loginid,"");
//                    String userPW = sharedPreferences.getString(loginid+"PW","");
//                    if(userID.equals("")){
//                        Toast.makeText(activity_login.this,"회원정보가 없어요.",Toast.LENGTH_SHORT).show();
//                    }else if(!userID.equals(loginid)||!userPW.equals(loginpwd)){
//                        Toast.makeText(activity_login.this,"회원정보를 확인해주세요.",Toast.LENGTH_SHORT).show();
//                    }
//                    else{
//                        // 로그인 기능
//
//
//                        SharedPreferences pref= getSharedPreferences("pref",0);
//                        SharedPreferences.Editor editor=pref.edit();
//                        editor.putString("login",loginid);
//                        editor.commit();
//                        Intent intent = new Intent(activity_login.this, activity_home.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                    break;
                case R.id.bt_signup: // 회원가입
                    Intent intent = new Intent(activity_login.this, activity_signup.class);
                    startActivity(intent);
                    break;
            }
        }
    }

}


    class LoginTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;

        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://49.50.172.215:8080/shareonfoot/loginMember.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&password="+strings[1];
                osw.write(sendMsg);
                osw.flush();
                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();

                } else {
                    Log.i("통신 결과", conn.getResponseCode()+"에러");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return receiveMsg;
        }
    }
