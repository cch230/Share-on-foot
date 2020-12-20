package com.example.shareonfoot.closet;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.shareonfoot.R;
import com.example.shareonfoot.VO.ClothesVO;
import com.example.shareonfoot.codi.fragment_codi;
import com.example.shareonfoot.util.ClothesListAdapter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.nostra13.universalimageloader.utils.L;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;

/* 그리드 사이즈 조절 방법 :
어댑터 변경, 그리드 사이즈 변경, 페이지사이즈 변경ㅆ
(small(4), medium(3), large(2)) 20p, 15p, 10p
*/

public class   TabFragment_Clothes_inCloset extends Fragment {

    fragment_closet parentFragment;
    String identifier; //프래그먼트의 종류를 알려줌
    Integer pos=10;
    String location;
    public RelativeLayout Cloth_Info;
    private static final int UPDATE_INTERVAL_MS = 120000;  // 1초
    private static final int FASTEST_UPDATE_INTERVAL_MS = 60000; // 0.5초
    Integer page = 0;
    RecyclerView rv_clothes;
    ArrayList<ClothesVO> clothesList = new ArrayList<ClothesVO>();
    Location mCurrentLocatiion;
    private LocationRequest locationRequest;
    // The entry point to the Fused Location Provider.
    private FusedLocationProviderClient mFusedLocationProviderClient; // Deprecated된 FusedLocationApi를 대체
    private LatLng mDefaultLocation = new LatLng(37.375280717973304, 126.63289979777781);
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    LatLng latLng=null;
    private boolean mLocationPermissionGranted;
    //리사이클러뷰 어댑터
    ClothesListAdapter clothesListAdapter;
    public static String ErrMag = "ErrMag";
    public String err;
    public static TabFragment_Clothes_inCloset newInstance(String location, String identifier) {

        Bundle args = new Bundle();
        args.putString("location", location);
        args.putString("identifier", identifier);  // 키값, 데이터

        TabFragment_Clothes_inCloset fragment = new TabFragment_Clothes_inCloset();
        fragment.setArguments(args);
        return fragment;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentFragment = ((fragment_closet) getParentFragment());

        Bundle args = getArguments(); // 데이터 받기
        if (args != null) {
            location = args.getString("location");
            identifier = args.getString("identifier");
        }
        clothesListAdapter = new ClothesListAdapter(getContext(),clothesList, R.layout.fragment_recyclerview);

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String coordinates[]={page.toString(),""};
        //Log.i("qe",String.valueOf(page));
        //현재 페이지수와 함께 웹서버에 옷 데이터 요청
        //new networkTask().execute(coordinates);
        //리사이클러 뷰 설정하기
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        rv_clothes = (RecyclerView) view.findViewById(R.id.tab_clothes_rv);
        rv_clothes.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_clothes.setAdapter(clothesListAdapter);
        rv_clothes.setNestedScrollingEnabled(true);
        rv_clothes.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if (!rv_clothes.canScrollVertically(-1)) {
                    //스크롤이 최상단이면 데이터를 갱신한다
                    /*clothesList.clear();
                    page=0;
                    new networkTask().execute(Integer.toString(page));
                    clothesListAdapter.notifyDataSetChanged();
                    rv_clothes.setAdapter(clothesListAdapter);
                    //Log.e("test","데이터 갱신");*/
                } else if (!rv_clothes.canScrollVertically(1)) {
                    String coordinates[]={(++page).toString()};
                    new networkTask().execute(coordinates);
                    clothesListAdapter.notifyDataSetChanged();
                    rv_clothes.setAdapter(clothesListAdapter);

                    Log.e("test", "페이지 수 증가");
                } else {
                }
            }
        });

        final SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //스크롤이 최상단이면 데이터를 갱신한다
                clothesList.clear();
                page = 0;
                String coordinates[]={page.toString()};
                new networkTask().execute(coordinates);
                clothesListAdapter.notifyDataSetChanged();
                Log.e("test", "데이터 갱신");
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }

    class networkTask extends AsyncTask<String, String, String> {
        String sendMsg, receiveMsg;
        StringBuffer Buffer = new StringBuffer();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String[] strings) {

            CookieHandler.setDefault( new CookieManager( null, CookiePolicy.ACCEPT_ALL ) );
            String get_json = "",tmp;

            URL url;
            try {
                url = new URL("http://49.50.172.215:8080/shareonfoot/category.jsp");
                HttpURLConnection conn = null;
                try {
                    conn = (HttpURLConnection) url.openConnection();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    err = ioException.toString();
                    Log.i(ErrMag, "1");
                }
                conn.setRequestMethod("POST"); // URL 요청에 대한 메소드 설정 : POST.
                conn.setRequestProperty("Accept-Charset", "UTF-8"); // Accept-Charset 설정.
                conn.setRequestProperty("Context_Type", "application/x-www-form-urlencoded;cahrset=UTF-8");
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                SharedPreferences sharedPreferences=getContext().getSharedPreferences("tab",0);
                pos=sharedPreferences.getInt("pos",10);
                Log.i("pos",pos.toString());
                switch (pos) {
                    case 0: //모든 옷 조회
                       tmp="share";
                        break;
                    case 1: //카테고리 top 조회
                        tmp="카페&디저트";
                        break;
                    case 2: //카테고리 bottom 조회
                        tmp="음식";
                        break;
                    case 3: //카테고리 suit 조회
                        tmp="스포츠";
                        break;
                    case 4: //카테고리 outer 조회
                        tmp="독서&연극";
                        break;
                    case 5: //카테고리 shoes 조회
                        tmp="포차";
                        break;
                    case 6: //카테고리 bag 조회
                        tmp="놀거리";
                        break;
                    default:
                        tmp="";
                        break;
                }
                // 서버에서 읽어오기 위한 스트림 객체
                sendMsg = "identifier=" + tmp+ "&page=" + strings[0] ;
                Log.i("qe",tmp);
                wr.write(sendMsg);
                wr.flush();
                wr.close();
                Log.i("conn",conn.getResponseMessage());
                if (conn.getResponseCode() == conn.HTTP_OK) {

                    // 서버에서 읽어오기 위한 스트림 객체
                    InputStreamReader isr = new InputStreamReader(
                            conn.getInputStream());
                    // 줄단위로 읽어오기 위해 BufferReader로 감싼다.
                    BufferedReader br = new BufferedReader(isr);
                    // 반복문 돌면서읽어오기
                    while (true) {
                        String line = br.readLine();
                        if (line == null) {

                            break;
                        }
                        Buffer.append(line);
                    }
                    br.close();
                    conn.disconnect();
                }
                get_json = Buffer.toString();
                Log.i("msg", "get_json: " + get_json);

            } catch (MalformedURLException e) {
                e.printStackTrace();
                err = e.toString();
                Log.i(ErrMag, "5");
            } catch (IOException e) {
                e.printStackTrace();
                err = e.toString();
                Log.i(ErrMag, err);
            }
            return get_json;
        }


        public void onPostExecute(String result) {
            super.onPostExecute(result);
            int i=0;
            Integer image=0;
            Log.d("onPostExecute:  ", " <<<<<onPostExecute>>>> ");
            ArrayList<String> jidx=new ArrayList();
            ArrayList<String> jname=new ArrayList();
            ArrayList<String> jcategory=new ArrayList();
            ArrayList<String> jstar=new ArrayList();
            ArrayList<String> jadress=new ArrayList();
            ArrayList<String> jreview=new ArrayList();
            ArrayList<Integer> jimage=new ArrayList();

            try {
                JSONArray jarray = new JSONObject(result).getJSONArray("store_info");
                if(jarray!=null){
                    final int numberOfItemsInResp =  jarray.length();
                    Log.i("teaq", String.valueOf(numberOfItemsInResp));

                    for ( i = 0; i < numberOfItemsInResp; i++){
                        JSONObject jsonObject = jarray.getJSONObject(i);
                        String idx = jsonObject.getString("store_jidx");
                        String star = jsonObject.getString("store_jstar");
                        String review = jsonObject.getString("store_jreview");
                        String adress = jsonObject.getString("store_jadress");
                        String name = jsonObject.getString("store_jname");
                        String category =  jsonObject.getString("store_jcategory");
                        jidx.add(idx);
                        jname.add(name);
                        jname.add(category);
                        jname.add(star);
                        jname.add(adress);
                        jname.add(review);
                        Log.i("teaq", name);

                        switch (pos) {
                            case 0: //모든 옷 조회
                                image=R.drawable.all;
                                break;
                            case 1: //카테고리 top 조회
                                image=R.drawable.desert;
                                break;
                            case 2: //카테고리 bottom 조회
                                image=R.drawable.food1;
                                break;
                            case 3: //카테고리 suit 조회
                                image=R.drawable.sports;
                                break;
                            case 4: //카테고리 outer 조회
                                image=R.drawable.movie;
                                break;
                            case 5: //카테고리 shoes 조회
                                image=R.drawable.soju;
                                break;
                            case 6: //카테고리 bag 조회
                                image=R.drawable.play;
                                break;
                            default:
                                image=10;
                                break;
                        }
                        jimage.add(image);
                        ClothesVO data = new ClothesVO();

                        data.setidx(idx);
                        data.setname(name);
                        data.setcategory(category);
                        data.setstar(star);
                        data.setadress(adress);
                        data.setreview(review);
                        data.setimage(image);
                        clothesListAdapter.addItem(data);
                        clothesListAdapter.notifyDataSetChanged();

                    }
                    //clothesList.clear();
                } else {
                    Toast.makeText(getContext(), "가까운 곳 없습니다.", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.e(ErrMag, e.toString());
            }
        }
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    //프래그먼트 갱신
    private void refresh(){
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.detach(this).attach(this).commit();
    }




/*
    String getCurrentAddress(LatLng latlng) {
        // 위치 정보와 지역으로부터 주소 문자열을 구한다.
        List<Address> addressList = null ;
        Geocoder geocoder = new Geocoder( getActivity(), Locale.getDefault());

        // 지오코더를 이용하여 주소 리스트를 구한다.
        try {
            addressList = geocoder.getFromLocation(latlng.latitude,latlng.longitude,1);
        } catch (IOException e) {
            Toast. makeText( getActivity(), "위치로부터 주소를 인식할 수 없습니다. 네트워크가 연결되어 있는지 확인해 주세요.", Toast.LENGTH_SHORT ).show();
            e.printStackTrace();
            return "주소 인식 불가" ;
        }

        if (addressList.size() < 1) { // 주소 리스트가 비어있는지 비어 있으면
            return "해당 위치에 주소 없음" ;
        }

        // 주소를 담는 문자열을 생성하고 리턴
        Address address = addressList.get(0);
        StringBuilder addressStringBuilder = new StringBuilder();
        for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
            addressStringBuilder.append(address.getAddressLine(i));
            if (i < address.getMaxAddressLineIndex())
                addressStringBuilder.append("\n");
        }

        return addressStringBuilder.toString();
    }

    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);

            List<Location> locationList = locationResult.getLocations();

            if (locationList.size() > 0) {
                Location location = locationList.get(locationList.size() - 1);

                mCurrentLocatiion = location;

            }
        }

    };



    public void setCurrentLocation(Location location, String markerTitle, String markerSnippet) {
        if (currentMarker != null) currentMarker.remove();

        LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
        latLng =currentLatLng;
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(currentLatLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));

        Log.i("lng",String.valueOf(latLng.latitude));

    }

    private void getDeviceLocation() {
        try {
            if (mLocationPermissionGranted) {
                mFusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }

    }*/

}
