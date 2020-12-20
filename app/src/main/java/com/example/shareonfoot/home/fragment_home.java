package com.example.shareonfoot.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.example.shareonfoot.codi.MyPagerAdapter;
import com.example.shareonfoot.util.OnBackPressedListener;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;

import static android.app.Activity.RESULT_OK;
import static android.view.MotionEvent.*;
import static android.view.MotionEvent.ACTION_DOWN;

public class fragment_home extends Fragment implements OnBackPressedListener {

    ViewGroup viewGroup;
    Toast toast;
    long backKeyPressedTime;
    int ADD_CLOTHES = 100;
    Activity activity;
    private ViewPager finalPager_recommend;
    private MyPagerAdapter pagerAdapter_recommend;
    private TabLayout tabLayout_favorite;
    public TabPagerAdapter_home pagerAdapter_favorite;
    private ViewPager finalPager_favorite;
    DrawerLayout drawer;
    public RelativeLayout Cloth_Info;
    public RelativeLayout Cloth_Info_edit;
    //private ImageView reco;
    public TextView weekday_text;
    private ImageView iv_codi_image;
    private ImageView iv_codi_image1;
    private ImageView iv_codi_image2;
    private ImageView iv_codi_image3;
    private ImageView iv_codi_image4;
    private ImageView iv_codi_image5;
    private ImageView iv_heart;
    ArrayList<ImageView> iv_codi_list;
    ArrayList<Integer> index_resourceID;

    ImageView selected_iv;
    int selected_index;
    private boolean i = true;

    public static fragment_home newInstance() {

        Bundle args = new Bundle();
        fragment_home fragment = new fragment_home();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.frag_home, container, false);
        toast = Toast.makeText(getContext(), "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT);
        iv_codi_list = new ArrayList<ImageView>(Arrays.asList(iv_codi_image1, iv_codi_image2, iv_codi_image3, iv_codi_image4, iv_codi_image5));
        index_resourceID = new ArrayList<Integer>(Arrays.asList(R.id.iv_codi1, R.id.iv_codi2, R.id.iv_codi3, R.id.iv_codi4, R.id.iv_codi5));


        //뷰페이저에 코디 개수만큼 프래그먼트 추가


        // 추천 코디 개수만큼 이미지 보여주기
        int i = 0;


        // 안 쓰는 이미지뷰 끄기


        //viewPager.setCurrentItem(0); //첫페이지 초기 설정
        selected_index = 0;
        selected_iv = iv_codi_list.get(0);
        return viewGroup;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            activity = (Activity) context;
            ((activity_home) activity).setOnBackPressedListener(this);
        }
        if(pagerAdapter_recommend!=null)
            pagerAdapter_recommend.notifyDataSetChanged();

    }


    @Override
    public void onStart() {
        super.onStart();


        iv_codi_image = getView().findViewById(R.id.iv_codi_image);
        iv_codi_image1 = getView().findViewById(R.id.bottom1);
        iv_codi_image2 = getView().findViewById(R.id.bottom2);
        iv_codi_image3 = getView().findViewById(R.id.bottom3);
        iv_codi_image4 = getView().findViewById(R.id.bottom4);
        iv_codi_image5 = getView().findViewById(R.id.bottom5);
        TextView child1 = getView().findViewById(R.id.child1);
        TextView child2 = getView().findViewById(R.id.child2);
        TextView child3 = getView().findViewById(R.id.child3);
        TextView child4 = getView().findViewById(R.id.child4);
        iv_heart = getView().findViewById(R.id.iv_heart);


        BtnOnClickListener onClickListener = new BtnOnClickListener();
//        iv_heart.setOnClickListener(onClickListener);
        iv_codi_image1.setOnTouchListener(onClickListener);
        iv_codi_image2.setOnTouchListener(onClickListener);
        iv_codi_image3.setOnTouchListener(onClickListener);
        iv_codi_image4.setOnTouchListener(onClickListener);
        iv_codi_image5.setOnTouchListener(onClickListener);
        iv_heart.setOnTouchListener(onClickListener);
        // reco = (ImageView) getView().findViewById(R.id.reco);

        if (tabLayout_favorite == null) {
            //탭 목록 설정
            tabLayout_favorite = (TabLayout) getView().findViewById(R.id.favorite_tabLayout);
            tabLayout_favorite.addTab(tabLayout_favorite.newTab().setText("즐겨찾는 상점"));
            tabLayout_favorite.addTab(tabLayout_favorite.newTab().setText("즐겨찾는 음식"));
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
        //탭 페이저 설정 (탭 클릭시 바뀌는 화면)
        finalPager_recommend = (ViewPager) getView().findViewById(R.id.recommend_tab_Pager);
        pagerAdapter_recommend = new MyPagerAdapter(getChildFragmentManager());

        finalPager_recommend.setAdapter(pagerAdapter_recommend);

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
        } else if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast.show();
            return;
        } else if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            activity.finish();
            toast.cancel();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_CLOTHES && resultCode == RESULT_OK)
            ((activity_home) activity).refresh_clothes(fragment_home.this);
    }

    class BtnOnClickListener implements View.OnTouchListener {
        String res = "";

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int dp40 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,40,getResources().getDisplayMetrics());
            int dp50 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50,getResources().getDisplayMetrics());
            if (event.getAction() == ACTION_DOWN^event.getAction() == ACTION_MOVE) {
                switch (v.getId()) {
                    case R.id.bottom1:
                        iv_codi_image.setImageResource(R.drawable.gyeom1);
                        iv_codi_image1.getLayoutParams().height=dp50;
                        iv_codi_image1.getLayoutParams().width=dp50;
                        iv_codi_image1.requestLayout();
                        break;
                    case R.id.bottom2: 
                        iv_codi_image.setImageResource(R.drawable.gyeom2);
                        iv_codi_image2.getLayoutParams().height=dp50;
                        iv_codi_image2.getLayoutParams().width=dp50;
                        iv_codi_image2.requestLayout();
                        break;
                    case R.id.bottom3: 
                        iv_codi_image.setImageResource(R.drawable.gyeom3);
                        iv_codi_image3.getLayoutParams().height=dp50;
                        iv_codi_image3.getLayoutParams().width=dp50;
                        iv_codi_image3.requestLayout();
                        break;
                    case R.id.bottom4: 
                        iv_codi_image.setImageResource(R.drawable.gyeom4);
                        iv_codi_image4.getLayoutParams().height=dp50;
                        iv_codi_image4.getLayoutParams().width=dp50;
                        iv_codi_image4.requestLayout();
                        break;
                    case R.id.bottom5: 
                        iv_codi_image.setImageResource(R.drawable.gyeom5);
                        iv_codi_image5.getLayoutParams().height=dp50;
                        iv_codi_image5.getLayoutParams().width=dp50;
                        iv_codi_image5.requestLayout();
                        break;
                    case R.id.iv_heart: 
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("heart", 0);
                        if (i) {
                            iv_heart.setImageResource(R.drawable.heart_empty_white);
                            i = false;
                        } else {
                            iv_heart.setImageResource(R.drawable.heart_color);
                            i = true;
                        }
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("heart", i);
                        editor.commit();
                        break;
                }
            }
            else {
                iv_codi_image.setImageResource(R.drawable.gyeom);
                iv_codi_image1.getLayoutParams().height=dp40;
                iv_codi_image1.getLayoutParams().width=dp40;
                iv_codi_image2.getLayoutParams().height=dp40;
                iv_codi_image2.getLayoutParams().width=dp40;
                iv_codi_image3.getLayoutParams().height=dp40;
                iv_codi_image3.getLayoutParams().width=dp40;
                iv_codi_image4.getLayoutParams().height=dp40;
                iv_codi_image4.getLayoutParams().width=dp40;
                iv_codi_image5.getLayoutParams().height=dp40;
                iv_codi_image5.getLayoutParams().width=dp40;
            }
            return false;
        }

    }
}
               /* int dp40;
                int dp50;
                switch (view.getId()) {
                    case R.id.iv_codi1 :
                    case R.id.iv_codi2 :
                    case R.id.iv_codi3 :
                    case R.id.iv_codi4 :
                    case R.id.iv_codi5 :
                        if(selected_index!=index_resourceID.indexOf(resourceID)){
                            //이전 선택 이미지뷰 작게 변경
                            dp40 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,40,getResources().getDisplayMetrics());
                            dp50 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50,getResources().getDisplayMetrics());
                            selected_iv.getLayoutParams().height=dp40;
                            selected_iv.getLayoutParams().width=dp40;
                            selected_iv.requestLayout();
                            //현재 선택된 페이지로 바꾸고 이미지뷰 크게 변경
                            selected_index = index_resourceID.indexOf(resourceID);
                            selected_iv = iv_codi_list.get(selected_index);
                            selected_iv.getLayoutParams().height=dp50;
                            selected_iv.getLayoutParams().width=dp50;
                            selected_iv.requestLayout();
                        }*/
