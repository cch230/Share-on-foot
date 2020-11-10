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

import com.example.shareonfoot.R;


public class recommendedItemFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String paramBoardNo = "boardInfo";

    // TODO: Rename and change types of parameters

    public recommendedItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_recommended_item, container, false);



        FrameLayout fl_recommendedItem = v.findViewById(R.id.fl_recommended_item);
        ImageView iv_codi_image = v.findViewById(R.id.iv_codi_image);
        TextView child1 = v.findViewById(R.id.child1);
        TextView child2 = v.findViewById(R.id.child2);
        TextView child3 = v.findViewById(R.id.child3);
        TextView child4 = v.findViewById(R.id.child4);
        ImageView iv_heart= v.findViewById(R.id.iv_heart);
        TextView numHeart = v.findViewById(R.id.tv_numHeart);



        // 포함 옷 개수만큼 식별자 보여주기

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

                case R.id.iv_heart : //하트 클릭 로직 추가해야 함
                    break;

            }
        }
    }

}