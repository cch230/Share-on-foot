package com.example.shareonfoot.social.space.subfragment;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.example.shareonfoot.R;
import com.example.shareonfoot.social.space.activity_space;

/* 그리드 사이즈 조절 방법 :
어댑터 변경, 그리드 사이즈 변경, 페이지사이즈 변경
(small(4), medium(3), large(2)) 20p, 15p, 10p
*/

public class Fragment_UserspaceFeed extends Fragment {


    String identifier; //프래그먼트의 종류를 알려줌
    String size;

    int gridsize=2;
    String pageSize="8";

    int page=0;
    RecyclerView rv_post;



    public static Fragment_UserspaceFeed newInstance(String identifier, String size) {

        Bundle args = new Bundle();
        args.putString("identifier", identifier);  // 키값, 데이터
        args.putString("size", size);

        Fragment_UserspaceFeed fragment = new Fragment_UserspaceFeed();
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

        activity_space activity = (activity_space) getActivity();

        //리사이클러 뷰 설정하기
        View view = inflater.inflate(R.layout.layout_share, container, false);
        rv_post = (RecyclerView) view.findViewById(R.id.post_list);
        rv_post.setLayoutManager(new GridLayoutManager(getContext(), gridsize)); //그리드 사이즈 설정
        rv_post.setNestedScrollingEnabled(true);


        rv_post.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if (!rv_post.canScrollVertically(-1)) {
                    //스크롤이 최상단이면 데이터를 갱신한다
                    //page = 0;
                    //new networkTask().execute(Integer.toString(page));
                    //Log.e("test","데이터 갱신");
                }
                else if (!rv_post.canScrollVertically(1)) {
                    //스크롤이 최하단이면 웹서버에 다음 페이지 옷 데이터 요청
                    Log.e("test","페이지 수 증가");
                }
                else {
                }
            }
        });



        final SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //스크롤이 최상단이면 데이터를 갱신한다

                page=0;

                Log.e("test","데이터 갱신");
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        return view;
    }









    @Override
    public void onResume() {
        super.onResume();
    }

}
