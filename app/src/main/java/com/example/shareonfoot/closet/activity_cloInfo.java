package com.example.shareonfoot.closet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shareonfoot.Global;
import com.example.shareonfoot.R;
import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;

public class activity_cloInfo extends AppCompatActivity {

    String mode;

    LinearLayout ll_detail;
    public ImageView iv_image;
    public TextView tv_category;
    public TextView tv_detailcategory;
    public TextView tv_color;
    public TextView tv_season;
    public TextView tv_brand;
    public TextView tv_cloNo;
    public TextView tv_cloFavorite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloth_info);

        ll_detail = findViewById(R.id.ll_detail);

        try{
            mode = getIntent().getExtras().getString("mode");
        }catch(NullPointerException e){
            e.printStackTrace();
        }

        if(mode==null)
            mode = "social"; //my, social


        ll_detail = findViewById(R.id.ll_detail);

        iv_image = (ImageView) findViewById(R.id.iv_image);
        tv_category = (TextView) findViewById(R.id.tv_info_catergory);
        tv_detailcategory = (TextView) findViewById(R.id.tv_info_detailcategory);
        tv_color = (TextView) findViewById(R.id.tv_info_color);
        tv_season = (TextView) findViewById(R.id.tv_info_season);
        tv_brand = (TextView) findViewById(R.id.tv_info_brand);

        //iv_heart = (ImageView) findViewById(R.id.iv_heart);
        //iv_modify = (ImageView) findViewById(R.id.iv_modify);
        //iv_delete = (ImageView) findViewById(R.id.iv_delete);

        tv_cloNo = (TextView) findViewById(R.id.tv_clothes_no);
        tv_cloFavorite = (TextView) findViewById(R.id.tv_clothes_favorite);


        //BtnOnClickListener onClickListener = new BtnOnClickListener();
        //iv_heart.setOnClickListener(onClickListener);
        //iv_modify.setOnClickListener(onClickListener);
        //iv_delete.setOnClickListener(onClickListener);







        switch(mode){
            case "social": //소셜 모드일 때
                LinearLayout ll_closet_iconSet = findViewById(R.id.ll_closet_iconSet);
                ll_closet_iconSet.setVisibility(View.GONE);
                LinearLayout ll_share_iconSet = findViewById(R.id.ll_share_iconSet);
                ll_share_iconSet.setVisibility(View.VISIBLE);

                ImageView iv_inbox = findViewById(R.id.iv_inbox);
                iv_inbox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {







                    }
                });
                break;
        }

    }





}