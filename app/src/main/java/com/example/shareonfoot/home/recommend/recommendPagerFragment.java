package com.example.shareonfoot.home.recommend;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.shareonfoot.Global;

import com.example.shareonfoot.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link recommendPagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class recommendPagerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String boardParams = "recommendedBoards";

    // TODO: Rename and change types of parameters


    ImageView iv_codi1, iv_codi2, iv_codi3, iv_codi4, iv_codi5,iv_codi;
    ArrayList<ImageView> iv_codi_list;
    ArrayList<Integer> index_resourceID;

    ImageView selected_iv;
    int selected_index;



    //뷰페이저 선언
    private ViewPager viewPager;



    public recommendPagerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    public static recommendPagerFragment newInstance() {
        recommendPagerFragment fragment = new recommendPagerFragment();
        Bundle args = new Bundle();
        //args.putParcelableArrayList(boardParams, boards);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       // View view = inflater.inflate(R.layout.layout_recommend_viewpager, container, false);







        //Glide.with((iv_codi).getContext()).load(R.drawable.gyeom).into(iv_codi);

       /* iv_codi1 = view.findViewById(R.id.iv_codi1);
        iv_codi2 = view.findViewById(R.id.iv_codi2);
        iv_codi3 = view.findViewById(R.id.iv_codi3);
        iv_codi4 = view.findViewById(R.id.iv_codi4);
        iv_codi5 = view.findViewById(R.id.iv_codi5);

        BtnOnClickListener onClickListener = new BtnOnClickListener();
        iv_codi1.setOnClickListener(onClickListener);
        iv_codi2.setOnClickListener(onClickListener);
        iv_codi3.setOnClickListener(onClickListener);
        iv_codi4.setOnClickListener(onClickListener);
        iv_codi5.setOnClickListener(onClickListener);*/


        //뷰페이저 어댑터 설정
        //viewPager = (ViewPager) view.findViewById(R.id.viewPager) ;
        //viewPager.setOffscreenPageLimit(1); //캐싱을 해놓을 프래그먼트 개수







        //List<Integer> valuesList = new ArrayList<Integer>(feedMapByBoardNo.values());
        //int randomIndex = new Random().nextInt(valuesList.size());
        //Integer randomValue = valuesList.get(randomIndex);














        //뷰페이저에 코디 개수만큼 프래그먼트 추가



        // 추천 코디 개수만큼 이미지 보여주기
        int i=0;


        // 안 쓰는 이미지뷰 끄기


        viewPager.setCurrentItem(0); //첫페이지 초기 설정
        selected_index=0;
        selected_iv = iv_codi_list.get(0);


       // return view;
        return null;
    }

    class BtnOnClickListener implements Button.OnClickListener {
        String res="";

        @Override
        public void onClick(View view) {

        }
    }


    public static <K, V> K getKey(Map<K, V> map, V value) {

        for (K key : map.keySet()) {
            if (value.equals(map.get(key))) {
                return key;
            }
        }
        return null;
    }
}
