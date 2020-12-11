package com.example.shareonfoot.closet;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.shareonfoot.Global;

import com.example.shareonfoot.R;
import com.example.shareonfoot.home.activity_home;
import com.example.shareonfoot.util.OnBackPressedListener;
import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;

import static android.app.Activity.RESULT_OK;

public class fragment_closet extends Fragment implements OnBackPressedListener {

    ViewGroup viewGroup;
    Toast toast;
    long backKeyPressedTime;

    int ADD_CLOTHES = 100;
    int ADD_FROM_DB = 150;

    Activity activity;

    private TabLayout tabLayout;
    public TabPagerAdapter_closet pagerAdapter;
    private ViewPager finalPager;

    //Button shareButton;

    DrawerLayout drawer;

    LinearLayout ll_detail;
    LinearLayout ll_detail_edit;
    public RelativeLayout Cloth_Info;
    public RelativeLayout Cloth_Info_edit;
    public ImageView iv_image;
    public ImageView iv_edit_image;
    public TextView tv_category;
    public TextView tv_detailcategory;
    public TextView tv_color;
    public TextView tv_season;
    public TextView tv_brand;
    public TextView tv_size;
    public TextView tv_date;

    public ImageView iv_heart;
    public ImageView iv_modify;
    public ImageView iv_delete;
    public ImageView iv_save;
    public TextView tv_cloNo;
    public TextView tv_cloFavorite;
    public TextView tv_edit_category;
    public TextView tv_edit_season;
    public TextView tv_edit_date;
    public TextView tv_edit_color;
    public TextView tv_edit_detailcategory;
    public TextView tv_edit_brand;
    public TextView tv_edit_size;

    private FloatingActionMenu fam;
    private FloatingActionButton fabAdd, fabBring;
    
    public static fragment_closet newInstance() {

        Bundle args = new Bundle();

        fragment_closet fragment = new fragment_closet();
        fragment.setArguments(args);
        return fragment;



    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.frag_closet,container,false);
        toast = Toast.makeText(getContext(),"한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT);

        //shareButton = viewGroup.findViewById(R.id.share_closet);
        //shareButton.setVisibility(View.VISIBLE);

        ll_detail = viewGroup.findViewById(R.id.ll_detail);
        ll_detail_edit = viewGroup.findViewById(R.id.ll_detail_edit);

        drawer = viewGroup.findViewById(R.id.final_drawer_layout);

        Cloth_Info = (RelativeLayout) viewGroup.findViewById(R.id.cloth_info);
        Cloth_Info.setVisibility(View.GONE);
        Cloth_Info_edit = (RelativeLayout) viewGroup.findViewById(R.id.cloth_info_edit);
        Cloth_Info_edit.setVisibility(View.GONE);

        iv_image = (ImageView) viewGroup.findViewById(R.id.iv_image);
        iv_edit_image = (ImageView) viewGroup.findViewById(R.id.iv_edit_image);
        tv_category = (TextView) viewGroup.findViewById(R.id.tv_info_catergory);
        tv_detailcategory = (TextView) viewGroup.findViewById(R.id.tv_info_detailcategory);
        tv_color = (TextView) viewGroup.findViewById(R.id.tv_info_color);
        tv_season = (TextView) viewGroup.findViewById(R.id.tv_info_season);
        tv_brand = (TextView) viewGroup.findViewById(R.id.tv_info_brand);
        tv_date = (TextView) viewGroup.findViewById(R.id.tv_info_date);

        iv_heart = (ImageView) viewGroup.findViewById(R.id.iv_heart);
        iv_modify = (ImageView) viewGroup.findViewById(R.id.iv_modify);
        iv_delete = (ImageView) viewGroup.findViewById(R.id.iv_delete);
        iv_save = (ImageView) viewGroup.findViewById(R.id.iv_save);

        tv_cloNo = (TextView) viewGroup.findViewById(R.id.tv_clothes_no);
        tv_cloFavorite = (TextView) viewGroup.findViewById(R.id.tv_clothes_favorite);
        tv_edit_color = (TextView) viewGroup.findViewById(R.id.tv_edit_info_color);
        tv_edit_detailcategory = (TextView) viewGroup.findViewById(R.id.tv_edit_detailcategory);
        tv_edit_brand = (TextView) viewGroup.findViewById(R.id.tv_edit_brand);
        tv_edit_size = (TextView) viewGroup.findViewById(R.id.tv_edit_size);
        tv_edit_category = (TextView) viewGroup.findViewById(R.id.tv_edit_catergory);
        tv_edit_season = (TextView) viewGroup.findViewById(R.id.tv_edit_season);
        tv_edit_date = (TextView) viewGroup.findViewById(R.id.tv_edit_date);


        //NavigationView navigationView = (NavigationView) viewGroup.findViewById(R.id.final_nav_view); //드로워 뷰


        //필터 버튼 클릭하면 드로워 열고 닫기
//        filterButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(drawer.isDrawerOpen(GravityCompat.START)) {
//                    drawer.closeDrawer(GravityCompat.START);
//                } else {
//                    drawer.openDrawer(GravityCompat.START);
//                }
//            }
//        });

        //필터(메뉴) 아이템 선택
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId())
//                {
//                    case R.id.menuitem1:
//                        Toast.makeText(getContext(), "SelectedItem 1", Toast.LENGTH_SHORT).show();
//                    case R.id.menuitem2:
//                        Toast.makeText(getContext(), "SelectedItem 2", Toast.LENGTH_SHORT).show();
//                    case R.id.menuitem3:
//                        Toast.makeText(getContext(), "SelectedItem 3", Toast.LENGTH_SHORT).show();
//                }
//
//                DrawerLayout drawer = viewGroup.findViewById(R.id.final_drawer_layout);
//                //drawer.closeDrawer(GravityCompat.START);
//                return true;
//            }
//        });

        if(tabLayout == null){
            //탭 목록 설정
            tabLayout = (TabLayout) viewGroup.findViewById(R.id.tabLayout);
            tabLayout.addTab(tabLayout.newTab().setText("모두"));
            tabLayout.addTab(tabLayout.newTab().setText("카페&디저트"));
            tabLayout.addTab(tabLayout.newTab().setText("음식"));
            tabLayout.addTab(tabLayout.newTab().setText("스포츠"));
            tabLayout.addTab(tabLayout.newTab().setText("독서&연극"));
            tabLayout.addTab(tabLayout.newTab().setText("포차"));
            tabLayout.addTab(tabLayout.newTab().setText("놀거리"));


            tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);


            //탭 페이저 설정 (탭 클릭시 바뀌는 화면)
            finalPager = (ViewPager) viewGroup.findViewById(R.id.tab_Pager);
            pagerAdapter = new TabPagerAdapter_closet(getChildFragmentManager(), tabLayout.getTabCount());
            finalPager.setAdapter(pagerAdapter);
            finalPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    SharedPreferences sharedPreferences=getContext().getSharedPreferences("tab",0);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putInt("pos",tab.getPosition());
                    editor.commit();
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


        //플로팅 액션 버튼 설정
        fabAdd = (FloatingActionButton) viewGroup.findViewById(R.id.fab_add_photo);
        fabBring = (FloatingActionButton) viewGroup.findViewById(R.id.fab_bring_data);
        fam = (FloatingActionMenu) viewGroup.findViewById(R.id.fab_menu);

        //handling menu status (open or close)
        fam.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                if (opened) {
                    //Toast.makeText(getContext(), "Menu is opened", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(getContext(), "Menu is closed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        fam.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(fam.isOpened()){
                    fam.close(true);
                }
                else{
                    fam.open(true);
                }

            }
        });

        //fam.open(true);
        //fam.close(true);
        fam.setClosedOnTouchOutside(true);



        return viewGroup;
    }


    //액티비티에 재부착될 때의 처리.
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

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String buyDate = String.format("%d-%02d-%02d",year,monthOfYear+1,dayOfMonth);
            tv_edit_date.setText(buyDate);
            tv_edit_date.setTextColor(Color.parseColor("#000000"));
        }

    };


    @Override
    public void onResume() {
        super.onResume();

        if(tabLayout == null){
            //탭 목록 설정
            tabLayout = (TabLayout) viewGroup.findViewById(R.id.tabLayout);
            tabLayout.addTab(tabLayout.newTab().setText("모두"));
            tabLayout.addTab(tabLayout.newTab().setText("카페&디저트"));
            tabLayout.addTab(tabLayout.newTab().setText("음식"));
            tabLayout.addTab(tabLayout.newTab().setText("스포츠"));
            tabLayout.addTab(tabLayout.newTab().setText("독서&연극"));
            tabLayout.addTab(tabLayout.newTab().setText("포차"));
            tabLayout.addTab(tabLayout.newTab().setText("놀거리"));

            tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);


            //탭 페이저 설정 (탭 클릭시 바뀌는 화면)
            finalPager = (ViewPager) viewGroup.findViewById(R.id.tab_Pager);
            pagerAdapter = new TabPagerAdapter_closet(getChildFragmentManager(), tabLayout.getTabCount());
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

    //뒤로 가기 버튼이 눌렸을 경우 드로워(메뉴)를 닫는다.
    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (Cloth_Info_edit.getVisibility() == View.VISIBLE) {
            Cloth_Info_edit.setVisibility(View.GONE);
        } else if (Cloth_Info.getVisibility() == View.VISIBLE) {
            Cloth_Info.setVisibility(View.GONE);
            fam.setVisibility(View.VISIBLE);
        } else if(System.currentTimeMillis() > backKeyPressedTime + 2000){
            backKeyPressedTime = System.currentTimeMillis();
            toast.show();
            return;
        } else if(System.currentTimeMillis() <= backKeyPressedTime + 2000){
            activity.finish();
            toast.cancel();
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_CLOTHES && resultCode == RESULT_OK)
            ((activity_home)activity).refresh_clothes(fragment_closet.this);
        else if(requestCode == ADD_FROM_DB && resultCode == RESULT_OK){
            ((activity_home)activity).refresh_clothes(fragment_closet.this);
        }

    }

    //커스텀 함수


    
}