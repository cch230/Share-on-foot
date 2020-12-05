package com.example.shareonfoot.home.mySpace;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.shareonfoot.Global;

import com.example.shareonfoot.R;
import com.example.shareonfoot.activity_login;
import com.example.shareonfoot.activity_profile;
import com.example.shareonfoot.home.activity_home;
import com.example.shareonfoot.social.space.subfragment.TabPagerAdapter_space;
import com.example.shareonfoot.util.NumFormat;
import com.example.shareonfoot.util.OnBackPressedListener;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;

import static com.example.shareonfoot.util.NumFormat.formatNumStringZero;

public class fragment_mySpace extends Fragment implements OnBackPressedListener {


    Toast toast;
    long backKeyPressedTime;

    //int ADD_BOARD = 8080;

    Activity activity;

    private TabLayout tabLayout;
    public TabPagerAdapter_mySpace pagerAdapter;
    private ViewPager finalPager;
    LinearLayout drawer;
    private TextView tv_nickname;
    private ImageButton imageButton;


    String myID;
    public String targetID,targetNAME;
    Button bt_follow;

    TextView tv_numFollower;

    int gridsize=2;
    String pageSize="8";


    public static fragment_mySpace newInstance() {

        Bundle args = new Bundle();

        fragment_mySpace fragment = new fragment_mySpace();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_space, container,false);
        toast = Toast.makeText(getContext(),"한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT);
        SharedPreferences pref= getContext().getSharedPreferences("pref",0);
        targetID = pref.getString("login","");
        targetNAME= pref.getString(targetID+"NAME","");
        //프사 설정
        ImageView iv_profileImage = v.findViewById(R.id.iv_profileImage);
        //아이디 설정
        TextView tv_id = v.findViewById(R.id.tv_id);
        tv_id.setText("@"+targetID);
        //닉네임 설정
        tv_nickname = v.findViewById(R.id.tv_nickname);
        tv_nickname.setText(targetNAME);
        //게시물, 팔로워, 팔로잉 수 설정
        TextView tv_numBoard = v.findViewById(R.id.tv_numBoard);
        tv_numFollower = v.findViewById(R.id.tv_numFollower);
        TextView tv_numFollowing = v.findViewById(R.id.tv_numFollowing);
         //소개글 설정
        TextView tv_pfContents = v.findViewById(R.id.tv_pfContents);
        imageButton=v.findViewById(R.id.camera);

        LinearLayout ll_following_friends = v.findViewById(R.id.ll_following_friends);

        //팔로우 여부 설정
        //bt_follow = v.findViewById(R.id.bt_follow);
     /*   if(myID.equals(targetID)){
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
        }*/


        toast = Toast.makeText(getContext(),"한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT);

        drawer = v.findViewById(R.id.final_drawer_layout);

        //BtnOnClickListener onClickListener = new BtnOnClickListener();


        if(tabLayout == null){
            //탭 목록 설정
            tabLayout = v.findViewById(R.id.tabLayout);
            tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.grid));
            tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.heart_empty));



            //탭 페이저 설정 (탭 클릭시 바뀌는 화면)
            finalPager = v.findViewById(R.id.tab_Pager);
            pagerAdapter = new TabPagerAdapter_mySpace(getChildFragmentManager(), tabLayout.getTabCount());
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


        //로그아웃 버튼
        Button bt_logout = v.findViewById(R.id.bt_logout);
        bt_logout.setVisibility(View.VISIBLE);
        bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getContext().getSharedPreferences("pref",0);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("login","");
                editor.commit();
                startActivity(new Intent(getContext(), activity_login.class));
                ActivityCompat.finishAffinity(getActivity());
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), camera.class));
                ActivityCompat.finishAffinity(getActivity());
            }
        });

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            activity = (Activity) context;
            ((activity_home)activity).setOnBackPressedListener(this);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

    }


    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis() > backKeyPressedTime + 2000){
            backKeyPressedTime = System.currentTimeMillis();
            toast.show();
            return;
        } else if(System.currentTimeMillis() <= backKeyPressedTime + 2000){
            activity.finish();
            toast.cancel();
        }
    }












    @Override
    public void onResume() {
        super.onResume();
        //activity.setOnBackPressedListener(this);
    }


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