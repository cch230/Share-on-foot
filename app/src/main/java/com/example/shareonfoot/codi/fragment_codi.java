package com.example.shareonfoot.codi;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.CycleInterpolator;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.example.shareonfoot.R;
import com.example.shareonfoot.home.activity_home;
import com.example.shareonfoot.util.OnBackPressedListener;
import com.example.shareonfoot.util.Location;
import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.Buffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import io.reactivex.Maybe;

import static android.app.Activity.RESULT_OK;

public class fragment_codi extends Fragment implements OnBackPressedListener, OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    ViewGroup viewGroup;
    Toast toast;
    long backKeyPressedTime;
    private GoogleMap mMap;

    int MAKE_CODI = 120;
    int WEATHER_CODI = 191;
    int RECO_CODI = 255;

    Activity activity;
    private PolylineOptions polylineOptions;
    private ArrayList<LatLng> arrayPoints;
    private ArrayList<MarkerOptions> arrayMarkerOptions;
    private ViewPager finalPager;

    List<Location> locationList = new ArrayList<Location>();

    DrawerLayout drawer;
    public RelativeLayout Cloth_Info;
    public RelativeLayout Cloth_Info_edit;
    public ImageView iv_image;
    public ImageView iv_edit_image;
    public TextView theme;
    public TextView tv_category;
    public TextView tv_detailcategory;
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
    public TextView tv_edit_name;
    public TextView tv_edit_detailcategory;
    public TextView tv_edit_brand;
    public TextView weekday;
    private MapView mapView=null;
    public static String ErrMag="ErrMag";
    public String err;
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private FloatingActionMenu fam;
    private FloatingActionButton fabMake, fabRecommend;

    public static fragment_codi newInstance() {

        Bundle args = new Bundle();

        fragment_codi fragment = new fragment_codi();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.frag_codi,container,false);
        toast = Toast.makeText(getContext(),"한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT);
        mapView = (MapView)viewGroup.findViewById(R.id.map);
        mapView.getMapAsync(this);
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

        drawer = getView().findViewById(R.id.final_drawer_layout);

        Cloth_Info = (RelativeLayout) getView().findViewById(R.id.cloth_info);
        Cloth_Info.setVisibility(View.GONE);
        Cloth_Info_edit = (RelativeLayout) getView().findViewById(R.id.cloth_info_edit);
        Cloth_Info_edit.setVisibility(View.GONE);

        iv_image = (ImageView) getView().findViewById(R.id.iv_codi_image);
        iv_edit_image = (ImageView) getView().findViewById(R.id.iv_edit_image);
        tv_category = (TextView) getView().findViewById(R.id.tv_info_catergory);
        tv_detailcategory = (TextView) getView().findViewById(R.id.tv_info_detailcategory);
        tv_season = (TextView) getView().findViewById(R.id.tv_info_season);
        tv_brand = (TextView) getView().findViewById(R.id.tv_info_brand);
        tv_date = (TextView) getView().findViewById(R.id.tv_info_date);

        iv_heart = (ImageView) getView().findViewById(R.id.iv_heart);
        iv_modify = (ImageView) getView().findViewById(R.id.iv_modify);
        iv_delete = (ImageView) getView().findViewById(R.id.iv_delete);
        iv_save = (ImageView) getView().findViewById(R.id.iv_save);

        tv_cloNo = (TextView) getView().findViewById(R.id.tv_clothes_no);
        tv_cloFavorite = (TextView) getView().findViewById(R.id.tv_clothes_favorite);
        tv_edit_name = (TextView) getView().findViewById(R.id.tv_info_color);
        tv_edit_detailcategory = (TextView) getView().findViewById(R.id.tv_edit_detailcategory);
        tv_edit_brand = (TextView) getView().findViewById(R.id.tv_edit_brand);
        weekday = (TextView) getView().findViewById(R.id.tabLayout);

        tv_edit_category = (TextView) getView().findViewById(R.id.tv_edit_catergory);
        tv_edit_season = (TextView) getView().findViewById(R.id.tv_edit_season);
        tv_edit_date = (TextView) getView().findViewById(R.id.tv_edit_date);
        theme = (TextView) getView().findViewById(R.id.day_theme);

        final String[] Category = {""};


        // BitmapDescriptorFactory 생성하기 위한 소스

        MapsInitializer.initialize(requireActivity());
        mapView = (MapView)getView().findViewById(R.id.map);
        mapView.getMapAsync(this);
        arrayPoints = new ArrayList<LatLng>();
            //탭 목록 설정
            String weekDay;

            // SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US); // 특정 언어로 출력하고 싶은 경우
            SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());

            Calendar calendar = Calendar.getInstance();
            weekDay = dayFormat.format(calendar.getTime());

            Toast.makeText(getContext(), weekDay, Toast.LENGTH_SHORT ).show();

            weekday.setText(weekDay);
            switch (day_return(weekDay)) {
                case 1:
                    theme.setText("오늘은 카페랑 디저트!!");

                    break;
                case 2:
                    theme.setText("오늘은 맛집이야!!");

                    break;
                case 3:
                    theme.setText("오늘은 디저트 먹으러!!");


                    break;
                case 4:
                    theme.setText("오늘은 마음의 양식!!");

                    break;
                case 6:
                    theme.setText("오늘은 머리하러 가자!!");

                    break;
                case 7:
                    theme.setText("오늘은 전부 다!!");

                    break;
            }



        //플로팅 액션 버튼 설정

        //fabAdd = (FloatingActionButton) getView().findViewById(R.id.fab_add_photo);
        fabMake = (FloatingActionButton) getView().findViewById(R.id.fab_make_codi);
        fabRecommend = (FloatingActionButton) getView().findViewById(R.id.fab_recommend_codi);
        fam = (FloatingActionMenu) getView().findViewById(R.id.fab_menu);

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

        //handling each floating action button clicked
        //fabAdd.setOnClickListener(onClickListener);


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

        fam.setIconAnimationOpenInterpolator(new CycleInterpolator(-0.5f));
        fam.setIconAnimationCloseInterpolator(new CycleInterpolator(-0.75f));
        fam.setClosedOnTouchOutside(true);
        fam.getMenuIconView().setColorFilter(Color.parseColor("#000000"));

    }

    public int day_return(String day){
        if(day.equals("월요일")) return 1;
        else if(day.equals("화요일")) return 2;
        else if(day.equals("수요일")) return 3;
        else if(day.equals("목요일")) return 4;
        else if(day.equals("금요일")) return 5;
        else if(day.equals("토요일")) return 6;
        else return 7;
    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            tv_edit_date.setText(year + "년" + monthOfYear + "월" + dayOfMonth +"일");
            Toast.makeText(getContext(), year + "년" + monthOfYear + "월" + dayOfMonth +"일", Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    //뒤로 가기 버튼이 눌렸을 경우 드로워(메뉴)를 닫는다.
    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(fam.isOpened()){
            fam.close(true);
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//액티비티가 처음 생성될 때 실행되는 함수

        if(mapView != null)
        {
            mapView.onCreate(savedInstanceState);
        }
    }


    @Override
    public void onMapClick(LatLng latLng) {
        MarkerOptions markerOptions = new MarkerOptions();
        mMap.addMarker(markerOptions);
        //add marker
        markerOptions.position(latLng);
        mMap.addMarker(markerOptions);
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
        polylineOptions = new PolylineOptions();
        polylineOptions.color(Color.YELLOW);

        polylineOptions.width(8);
        // 맵셋팅
        arrayPoints.add(latLng);
        polylineOptions.addAll(arrayPoints);

        mMap.addPolyline(polylineOptions);
    }

    //클릭 리스너
    class BtnOnClickListener implements Button.OnClickListener {
        String res="";

        @Override
        public void onClick(View view) {

            Intent intent;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MAKE_CODI && resultCode == RESULT_OK)
            ((activity_home)activity).refresh_codi(fragment_codi.this);
        else if(requestCode == WEATHER_CODI && resultCode == RESULT_OK)
            ((activity_home)activity).refresh_codi(fragment_codi.this);
        else if(requestCode == RECO_CODI && resultCode == RESULT_OK)
            ((activity_home)activity).refresh_codi(fragment_codi.this);

    }

    //커스텀 함수
    //커스텀 함수
    @Override
    public void onMapReady(final GoogleMap map) {

        mMap = map;
        MarkerOptions markerOptions = new MarkerOptions();
        String coordinates[] = {"37.375280717973304", "126.63289979777781"};
        double lat = Double.parseDouble(coordinates[0]);
        double lng = Double.parseDouble(coordinates[1]);
        LatLng position = new LatLng(lat, lng);
        GooglePlayServicesUtil.isGooglePlayServicesAvailable(getContext());
        markerOptions.position(position);
        mMap.addMarker(markerOptions);
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker2));
        // 맵 위치이동.

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15));
        mMap.setOnMapClickListener((GoogleMap.OnMapClickListener) this);
        mMap.setOnMapLongClickListener((GoogleMap.OnMapLongClickListener) this);

      //  mMap.setOnInfoWindowClickListener((GoogleMap.OnInfoWindowClickListener) this); //정보창 클릭 리스너(마커 삭제 이벤트)
        new NearestTask().execute(coordinates);


    }
    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onLowMemory();
    }

// polyline 생성

    public void drawPolyline(){

        polylineOptions = new PolylineOptions();

        polylineOptions.color(Color.RED);
        polylineOptions.width(5);

        polylineOptions.addAll(arrayPoints);

        mMap.addPolyline(polylineOptions);

    }

    @Override

    public void onMapLongClick(LatLng latLng) {

        mMap.clear();
        arrayPoints.clear();


    }

    class NearestTask extends AsyncTask<String, String, String> {
        String sendMsg, receiveMsg;
        StringBuffer Buffer = new StringBuffer();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String get_json = "";
            URL url;
            try {
                url = new URL("http://49.50.172.215:8080/shareonfoot/data.jsp");

                HttpURLConnection conn = null;
                try {
                    conn = (HttpURLConnection) url.openConnection();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    err = ioException.toString();
                    Log.i(ErrMag, "1");
                }
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                try {
                    conn.setRequestMethod("POST");
                } catch (ProtocolException protocolException) {
                    protocolException.printStackTrace();
                    err = protocolException.toString();
                    Log.i(ErrMag, "2");
                }
                OutputStreamWriter osw = null;
                try {
                    osw = new OutputStreamWriter(conn.getOutputStream());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    err = ioException.toString();
                    Log.i(ErrMag, "3");
                }
                Float lng = Float.valueOf(strings[0]);
                Float lat = Float.valueOf(strings[0]);
                sendMsg = "lng=" + lng + "&lat=" + lat;
                osw.write(sendMsg);
                try {
                    osw.flush();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    err = ioException.toString();
                    Log.i(ErrMag, "4");
                }
                if (conn != null) {
                    conn.setConnectTimeout(20000);
                  //  conn.setUseCaches(false);
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
                }
                get_json = Buffer.toString();
                Log.d("msg", "get_json: " + get_json);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                err = e.toString();
                Log.i(ErrMag, "5");
            } catch (IOException e) {
                e.printStackTrace();
                err = e.toString();
                Log.i(ErrMag, "6");
            }
            return get_json;
        }


        public void onPostExecute(String result) {
            super.onPostExecute(result);
            List<Location> list = new ArrayList<>();
            int i=0;
            Log.d("onPostExecute:  ", " <<<<<onPostExecute>>>> ");
            try {
                JSONArray jarray = new JSONObject(result).getJSONArray("store_data");
                 Location location = new Location();
                if(jarray!=null){
                    while (jarray != null) {
                        JSONObject jsonObject = jarray.getJSONObject(i);
                        String name = jsonObject.getString("store_name");
                        Float lng = Float.valueOf(jsonObject.getString("store_lng"));
                        Float lat = Float.valueOf(jsonObject.getString("store_lat"));
                        location.setname(name);
                        location.setlng(lng);
                        location.setlat(lat);
                        // null을 가끔 못 읽어오는 때가 있다고 하기에 써봄
                        //String Start = jsonObject.optString("START_TIME", "text on no value");
                        //String Stop = jsonObject.optString("STOP_TIME", "text on no value");
                        //String REG = jsonObject.optString("REG_TIME", "text on no value");
                        Log.i("qw", name + "/" + lng.toString() + "/" + lat.toString());
                        locationList.addAll(list);
                        i++;
                    }
                }
                else {
                    Toast.makeText(getContext(), "가까운 곳 없습니다.", Toast.LENGTH_SHORT).show();
                }
                locationList.addAll(list);
            } catch (Exception e) {
                Log.e(ErrMag, "7");
            }
            if(!locationList.isEmpty()){
                MarkerOptions markerOptions = new MarkerOptions();

                for(Location location : locationList){
                    Float lat;
                    Float lng;
                    lat=location.getlat();
                    lng=location.getlng();
                    LatLng position=new LatLng(lat,lng);
                    markerOptions.position(position);
                    mMap.addMarker(markerOptions);
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker2));
                }
            }
        }
    }




/**
     * 현재 위치정보 조회.
     *
     * @return
     *//*

    public Maybe<Location> get_현재위치정보() {
        try {
            // 권한 체크.
            CheckUtil.Check(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED, "[ACCESS_FINE_LOCATION] 권한체크 에러", Exception.class);
            CheckUtil.Check(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED, "[ACCESS_COARSE_LOCATION] 권한체크 에러", Exception.class);

            // 위치정보 조회.
            LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);

            // 위치 정보 사용여부 조회.
            {
                CheckUtil.Check(get_위치정보_요청가능_여부(locationManager), "위치정보 사용불가능 상태", Exception.class);
            }

            // 네트워크 정보로부터 데이터 조회.
            {
                // 네트워크 정보로 부터 위치값 가져오기
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES
                            , this
                    );

                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    }
                }
            }

            // GPS 정보로부터 데이터 조회.
            {
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES,
                                this
                        );

                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        }
                    }
                }
            }

        } catch (Exception e) {
            Log.e(MiRunConstDefinition.LOG_NAME, "위치정보 획득 실패", e);
        }

        return MaybeUtil.JustNullable(location);
    }

    */
/**
     * 마커 그리기
     *
     * @param myLocationMaybe
     *//*

    public void setDrawMaker(Maybe<Location> myLocationMaybe) {

        MaybeUtil.Subscribe(myLocationMaybe,

                location -> {

                    // Creating a LatLng object for the current location
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

                    CameraPosition cp = new CameraPosition.Builder().target((latLng)).
                            zoom(17).
                            build();

                    // Showing the current location in Google Map
                    mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));

                    // 기존 마커 삭제.
                    if (marker != null) {
                        marker.remove();
                    }

                    // 마커 설정.
                    MarkerOptions optFirst = new MarkerOptions().
                            position(latLng).
                            icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)).
                            title(MiRunResourceUtil.GetString(R.string.label_record_current_position));

                    marker = mMap.addMarker(optFirst);
                },
                () -> MiRunViewUtil.ShowToast(R.string.label_emergency_request_location_fail));
    }
    */
/**
     * 달리는 경로 polyline 생성(pink)
     *
     * @param runningPoints
     *//*

    public void drawRunningPolyline(List<LatLng> runningPoints){
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.color(MiRunResourceUtil.GetColor(R.drawable.marker));
        polylineOptions.width(7);
        polylineOptions.addAll(runningPoints);
        mMap.addPolyline(polylineOptions);
    }

*/

}
