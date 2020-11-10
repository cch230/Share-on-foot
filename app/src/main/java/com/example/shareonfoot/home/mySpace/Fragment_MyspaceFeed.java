package com.example.shareonfoot.home.mySpace;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.shareonfoot.R;

/* 그리드 사이즈 조절 방법 :
어댑터 변경, 그리드 사이즈 변경, 페이지사이즈 변경
(small(4), medium(3), large(2)) 20p, 15p, 10p
*/

public class Fragment_MyspaceFeed extends Fragment {


    String identifier; //프래그먼트의 종류를 알려줌
    String size;

    int gridsize=2;
    String pageSize="8";

    int page=0;
    RecyclerView rv_post;



    String targetID;

    public static Fragment_MyspaceFeed newInstance(String identifier, String size) {

        Bundle args = new Bundle();
        args.putString("identifier", identifier);  // 키값, 데이터
        args.putString("size", size);

        Fragment_MyspaceFeed fragment = new Fragment_MyspaceFeed();
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        Bundle args = getArguments(); // 데이터 받기
        if(args != null)
        {
            identifier = args.getString("identifier");
            size = args.getString("size");

            switch (size){
                case "small":
                    gridsize = 4; //스몰 그리드 4x5
                    pageSize="150"; //스몰 페이지 사이즈 25*6
                    break;
                case "medium":
                    gridsize = 3; //미디엄 그리드 3x4
                    pageSize="102"; //미디엄 페이지 사이즈 17*6
                    break;
                case "large":
                    gridsize = 2; //라지 그리드 2x3
                    pageSize="42"; //라지 페이지 사이즈 7*6
                    break;
                case "xLarge":
                    gridsize = 1; //X라지 그리드 1x2
                    pageSize="30"; //X라지 페이지 사이즈 5*6
                    break;
            }
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //현재 페이지수와 함께 웹서버에 옷 데이터 요청

        fragment_mySpace parent = (fragment_mySpace) getParentFragment();
        targetID = parent.targetID;

        //리사이클러 뷰 설정하기
        View view = inflater.inflate(R.layout.layout_share, container, false);
        rv_post = (RecyclerView) view.findViewById(R.id.post_list);
        rv_post.setLayoutManager(new GridLayoutManager(getContext(), gridsize)); //그리드 사이즈 설정
        rv_post.setNestedScrollingEnabled(true);


        rv_post.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {

            }
        });


        final SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });


        return view;
    }









    @Override
    public void onResume() {
        super.onResume();
    }

}
