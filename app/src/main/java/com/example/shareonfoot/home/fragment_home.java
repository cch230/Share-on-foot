package com.example.shareonfoot.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.shareonfoot.R;
import com.example.shareonfoot.home.recommend.recommendPagerFragment;
import com.example.shareonfoot.util.OnBackPressedListener;
import com.bumptech.glide.Glide;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.tabs.TabLayout;
import com.ssomai.android.scalablelayout.ScalableLayout;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import retrofit2.Call;

import static android.app.Activity.RESULT_OK;

public class fragment_home extends Fragment implements OnBackPressedListener {

    ViewGroup viewGroup;
    Toast toast;
    long backKeyPressedTime;
    int ADD_CLOTHES = 100;
    Activity activity;
    private TabLayout tabLayout_favorite;
    public TabPagerAdapter_home pagerAdapter_favorite;
    private ViewPager finalPager_favorite;
    DrawerLayout drawer;
    public RelativeLayout Cloth_Info;
    public RelativeLayout Cloth_Info_edit;
    public ImageView iv_heart;
    public TextView weekday_text;

    public static fragment_home newInstance() {

        Bundle args = new Bundle();
        fragment_home fragment = new fragment_home();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.frag_home,container,false);
        toast = Toast.makeText(getContext(),"한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT);

        return viewGroup;
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

        if(tabLayout_favorite == null){
            //탭 목록 설정
            tabLayout_favorite = (TabLayout) getView().findViewById(R.id.favorite_tabLayout);
            tabLayout_favorite.addTab(tabLayout_favorite.newTab().setText("즐겨찾는 상점"));
            tabLayout_favorite.addTab(tabLayout_favorite.newTab().setText("즐겨찾는 동네"));
            tabLayout_favorite.setTabGravity(TabLayout.GRAVITY_CENTER);

            //탭 페이저 설정 (탭 클릭시 바뀌는 화면)
            finalPager_favorite = (ViewPager) getView().findViewById(R.id.favorite_tab_Pager);
            pagerAdapter_favorite = new TabPagerAdapter_home(getChildFragmentManager(), tabLayout_favorite.getTabCount());
            finalPager_favorite.setAdapter(pagerAdapter_favorite);
            finalPager_favorite.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout_favorite));
            tabLayout_favorite.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    finalPager_favorite.setCurrentItem(tab.getPosition());
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
            ((activity_home)activity).refresh_clothes(fragment_home.this);
    }
    
}