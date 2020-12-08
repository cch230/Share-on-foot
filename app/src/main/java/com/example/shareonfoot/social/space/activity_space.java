package com.example.shareonfoot.social.space;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.shareonfoot.Global;

import com.example.shareonfoot.R;
import com.example.shareonfoot.social.space.subfragment.TabPagerAdapter_space;
import com.example.shareonfoot.util.NumFormat;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;

import static com.example.shareonfoot.util.NumFormat.formatNumStringZero;

public class activity_space extends AppCompatActivity {

    Toast toast;
    long backKeyPressedTime;

    //int ADD_BOARD = 8080;

    //Activity activity;

    private TabLayout tabLayout;
    public TabPagerAdapter_space pagerAdapter;
    private ViewPager finalPager;

    LinearLayout drawer;



    String myID;
    public String targetID;

    Button bt_follow;

    TextView tv_numFollower;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space);







        //프사 설정
        ImageView iv_profileImage = findViewById(R.id.iv_profileImage);
        //아이디 설정
        TextView tv_id = findViewById(R.id.tv_id);
        tv_id.setText("@"+targetID);
        //닉네임 설정
        TextView tv_nickname = findViewById(R.id.tv_nickname);
          //게시물, 팔로워, 팔로잉 수 설정
        TextView tv_numBoard = findViewById(R.id.tv_numBoard);
        tv_numFollower = findViewById(R.id.tv_numFollower);
        TextView tv_numFollowing = findViewById(R.id.tv_numFollowing);

        TextView tv_pfContents = findViewById(R.id.tv_pfContents);

        LinearLayout ll_following_friends = findViewById(R.id.ll_following_friends);

        //팔로우 여부 설정
        bt_follow = findViewById(R.id.bt_follow);
        if(myID.equals(targetID)){
            bt_follow.setVisibility(View.GONE);
            ll_following_friends.setVisibility(View.GONE);
        }
        else{
            bt_follow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            // 내가 팔로우한 사용자 중 현재 페이지 사용자를 팔로우한 사용자가 있는지.

        }



        


        toast = Toast.makeText(getApplicationContext(),"한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT);

        drawer = findViewById(R.id.final_drawer_layout);

        //BtnOnClickListener onClickListener = new BtnOnClickListener();


        if(tabLayout == null){
            //탭 목록 설정
            tabLayout = findViewById(R.id.tabLayout);
            tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.grid));
            tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.heart_empty));



            //탭 페이저 설정 (탭 클릭시 바뀌는 화면)
            finalPager = findViewById(R.id.tab_Pager);
            pagerAdapter = new TabPagerAdapter_space(getSupportFragmentManager(), tabLayout.getTabCount());
            finalPager.setAdapter(pagerAdapter);
            finalPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    finalPager.setCurrentItem(tab.getPosition());
                }
                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }
                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }
    }






    @Override
    public void onResume() {
        super.onResume();
        //activity.setOnBackPressedListener(this);
    }

    //뒤로 가기 버튼
    //@Override
    //public void onBackPressed() {
        //
    //}

    //클릭 리스너
    class BtnOnClickListener implements Button.OnClickListener {
        String res="";

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.header_add : //헤더- 추가 버튼
                    break;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == ADD_BOARD && resultCode == RESULT_OK)
//            ((activity_home)activity).refresh_share();
    }







    public void applyFollow(boolean is_following, String numFollow){
        if(!is_following){
            ViewCompat.setBackgroundTintList(bt_follow, ColorStateList.valueOf(Color.parseColor("#aa0055af")));
            bt_follow.setTextColor(Color.parseColor("#ffffff"));
            bt_follow.setText("팔로우");
        }else if(is_following){
            ViewCompat.setBackgroundTintList(bt_follow, ColorStateList.valueOf(Color.parseColor("#ffffff")));
            bt_follow.setTextColor(Color.parseColor("#000000"));
            bt_follow.setText("팔로잉");
        }

        numFollow = NumFormat.formatNumString(Integer.parseInt(numFollow),false); //수 포매팅
        tv_numFollower.setText(numFollow);
    }
}