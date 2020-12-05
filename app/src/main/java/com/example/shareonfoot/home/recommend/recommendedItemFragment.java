package com.example.shareonfoot.home.recommend;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shareonfoot.Global;
import com.example.shareonfoot.R;

import java.util.ArrayList;


public class recommendedItemFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String paramBoardNo = "boardInfo";

    // TODO: Rename and change types of parameters
    public recommendedItemFragment() {
        // Required empty public constructor
    }

    private ImageView iv_codi_image;
    private ImageView iv_codi_image1;
    private ImageView iv_codi_image2;
    private ImageView iv_codi_image3;
    private ImageView iv_codi_image4;
    private ImageView iv_codi_image5;

    public static recommendedItemFragment newInstance() {
        recommendedItemFragment fragment = new recommendedItemFragment();
        Bundle args = new Bundle();
        //args.putParcelableArrayList(paramBoardNo, boardInfo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_recommended_item, container, false);



        FrameLayout fl_recommendedItem = v.findViewById(R.id.fl_recommended_item);
        ImageView iv_codi_image = v.findViewById(R.id.iv_codi_image);
        ImageView iv_codi_image1 = v.findViewById(R.id.bottom1);
        ImageView iv_codi_image2 = v.findViewById(R.id.bottom2);
        ImageView iv_codi_image3= v.findViewById(R.id.bottom3);
        ImageView iv_codi_image4 = v.findViewById(R.id.bottom4);
        ImageView iv_codi_image5 = v.findViewById(R.id.bottom5);
        TextView child1 = v.findViewById(R.id.child1);
        TextView child2 = v.findViewById(R.id.child2);
        TextView child3 = v.findViewById(R.id.child3);
        TextView child4 = v.findViewById(R.id.child4);
        ImageView iv_heart= v.findViewById(R.id.iv_heart);
        TextView numHeart = v.findViewById(R.id.tv_numHeart);

        Glide.with(getContext()).load(R.drawable.gyeom);

        //하트 여부에 따라 아이콘 변경
        //String if_hearting = feedInfo.getBoard_if_hearting();
        /*if(if_hearting.equals("hearting")){
            iv_heart.setImageResource(R.drawable.heart_color);
        }
        else if(if_hearting.equals("not_hearting")){
            iv_heart.setImageResource(R.drawable.heart_empty_white);
        }*/
        //numHeart.setText(feedInfo.getBoard_numHeart()+"");

        //int childNum = selectedFeedList.size();
        TextView tv_childs[] = {child1,child2,child3,child4};

        // 포함 옷 개수만큼 식별자 보여주기

      /*  BtnOnClickListener onClickListener = new BtnOnClickListener();
        fl_recommendedItem.setOnClickListener(onClickListener);
        iv_heart.setOnClickListener(onClickListener);
        iv_codi_image1.setOnClickListener(onClickListener);
        iv_codi_image2.setOnClickListener(onClickListener);
        iv_codi_image3.setOnClickListener(onClickListener);
        iv_codi_image4.setOnClickListener(onClickListener);
        iv_codi_image5.setOnClickListener(onClickListener);*/
        // 포함 옷 개수만큼 식별자 보여주기
        //for(int i=0; i<childNum; i++){
         //
        BtnOnClickListener onClickListener = new BtnOnClickListener();
        fl_recommendedItem.setOnClickListener(onClickListener);
        iv_heart.setOnClickListener(onClickListener);


        // Inflate the layout for this fragment
        return v;
    }




    class BtnOnClickListener implements Button.OnClickListener {
        String res="";

        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()) {

               /* case R.id.bottom1 : //하트 클릭 로직 추가해야 함
                    iv_codi_image1.setImageResource(R.drawable.gyeom);
                    iv_codi_image.setImageResource(R.drawable.gyeom1);
                    break;
                case R.id.bottom2 : //하트 클릭 로직 추가해야 함
                    iv_codi_image2.setImageResource(R.drawable.gyeom);
                    iv_codi_image.setImageResource(R.drawable.gyeom2);
                    break;
                case R.id.bottom3 : //하트 클릭 로직 추가해야 함
                    iv_codi_image3.setImageResource(R.drawable.gyeom);
                    iv_codi_image.setImageResource(R.drawable.gyeom3);
                    break;
                case R.id.bottom4 : //하트 클릭 로직 추가해야 함
                    iv_codi_image4.setImageResource(R.drawable.gyeom);
                    iv_codi_image.setImageResource(R.drawable.gyeom4);
                    break;
                case R.id.bottom5 : //하트 클릭 로직 추가해야 함
                    iv_codi_image5.setImageResource(R.drawable.gyeom);
                    iv_codi_image.setImageResource(R.drawable.gyeom5);
                    break;*/
            }
        }
    }

}