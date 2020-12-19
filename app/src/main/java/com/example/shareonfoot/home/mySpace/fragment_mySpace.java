package com.example.shareonfoot.home.mySpace;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.shareonfoot.ConstantDefine;
import com.example.shareonfoot.ProgressCircleDialog;
import com.example.shareonfoot.R;
import com.example.shareonfoot.ScreenService;
import com.example.shareonfoot.activity_login;
import com.example.shareonfoot.home.activity_home;
import com.example.shareonfoot.home.mySpace.Image_processing;
import com.example.shareonfoot.util.OnBackPressedListener;
import com.google.android.material.tabs.TabLayout;
import com.googlecode.tesseract.android.TessBaseAPI;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;


public class fragment_mySpace extends Fragment implements OnBackPressedListener {


    Toast toast;
    long backKeyPressedTime;
    Switch switchOnOff;

    //int ADD_BOARD = 8080;

    Activity activity;

    private TabLayout tabLayout;
    public TabPagerAdapter_mySpace pagerAdapter;
    private ViewPager finalPager;

    LinearLayout drawer;
    String myID;
    public String targetID;

    Button bt_follow;
    ImageButton imageButton;
    TextView tv_numFollower;

    int gridsize=2;
    String pageSize="8";

    private boolean CameraOnOffFlag = true;

    private TessBaseAPI m_Tess; //Tess API reference
    private ProgressCircleDialog m_objProgressCircle = null;
    private MessageHandler m_messageHandler;

    private String mCurrentPhotoPath; //카메라로 찍은 사진 저장할 루트경로

    private String mDataPath = "";
    private final String[] mLanguageList = {"eng", "kor"};
    // View
    private Context mContext;
    private TextView m_ocrTextView;
    private Bitmap image;
    private boolean ProgressFlag = false;
    private ImageView imageView;



    public static fragment_mySpace newInstance() {

        Bundle args = new Bundle();

        fragment_mySpace fragment = new fragment_mySpace();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_space, container,false);
        toast = Toast.makeText(getContext(),"한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT);

        SharedPreferences pref= getContext().getSharedPreferences("pref",0);


        targetID = pref.getString("login","");




        //프사 설정
        ImageView iv_profileImage = v.findViewById(R.id.iv_profileImage);
        //아이디 설정
        TextView tv_id = v.findViewById(R.id.tv_id);
        tv_id.setText("@"+targetID);
        //닉네임 설정
        TextView tv_nickname = v.findViewById(R.id.tv_nickname);
        //게시물, 팔로워, 팔로잉 수 설정
        TextView tv_numBoard = v.findViewById(R.id.tv_numBoard);
        tv_numFollower = v.findViewById(R.id.tv_numFollower);
        TextView tv_numFollowing = v.findViewById(R.id.tv_numFollowing);

        TextView tv_pfContents = v.findViewById(R.id.tv_pfContents);
        imageButton=v.findViewById(R.id.camera);

        LinearLayout ll_following_friends = v.findViewById(R.id.ll_following_friends);

        //팔로우 여부 설정

        tv_nickname.setText(targetID);

        imageView=v.findViewById(R.id.iv_image);
     //  잠금화면 기능 활성화

        toast = Toast.makeText(getContext(),"한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT);

        drawer = v.findViewById(R.id.final_drawer_layout);

        //BtnOnClickListener onClickListener = new BtnOnClickListener();
        mContext = getContext();
        switchOnOff = (Switch) v.findViewById(R.id.lockscreenOnOff);

        switchOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    Intent intent = new Intent(activity.getApplicationContext(), ScreenService.class);
                    activity.startService(intent);
                } else {
                    Intent intent = new Intent(activity.getApplicationContext(), ScreenService.class);
                    activity.stopService(intent);
                }

            }
        });

        m_ocrTextView = v.findViewById(R.id.tv_view);
        imageButton = v.findViewById(R.id.camera);

        if (CameraOnOffFlag) {
            PermissionCheck();
            Tesseract();
        } else {
            //이미지 디코딩을 위한 초기화
            image = BitmapFactory.decodeResource(getResources(), R.drawable.sampledata);
            Test();
        }


        m_objProgressCircle = new ProgressCircleDialog(getContext());
        m_messageHandler = new MessageHandler();





        if(tabLayout == null){
            //탭 목록 설정
            tabLayout = v.findViewById(R.id.tabLayout);
            tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.grid));
            tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.heart_empty));



            //탭 페이저 설정 (탭 클릭시 바뀌는 화면)
            finalPager = v.findViewById(R.id.tab_Pager);
            pagerAdapter = new TabPagerAdapter_mySpace(getChildFragmentManager(), tabLayout.getTabCount());
            finalPager.setAdapter(pagerAdapter);
            finalPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    finalPager.setCurrentItem(tab.getPosition());
                }
                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }
                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CameraOnOffFlag) {
                    takePictureFromCameraIntent();
                } else {

                    processImage(v);
                }
            }
        });
        //로그아웃 버튼
        final Button bt_logout = v.findViewById(R.id.bt_logout);
        bt_logout.setVisibility(View.VISIBLE);
        bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getContext().getSharedPreferences("pref",0);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("login","");
                editor.commit();
                startActivity(new Intent(getContext(), activity_login.class));
                ActivityCompat.finishAffinity(getActivity());
            }
        });



        return v;
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

    }


    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis() > backKeyPressedTime + 2000){
            backKeyPressedTime = System.currentTimeMillis();
            toast.show();
            return;
        } else if(System.currentTimeMillis() <= backKeyPressedTime + 2000){
            activity.finish();
            toast.cancel();
        }
    }


    @Override
    public void onResume() {
        super.onResume();

        //activity.setOnBackPressedListener(this);
    }


    //클릭 리스너
    class BtnOnClickListener implements Button.OnClickListener {
        String res="";

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.header_add : //헤더- 추가 버튼
                    break;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @NonNull Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == ADD_BOARD && resultCode == RESULT_OK)
//            ((activity_home)activity).refresh_share();
        switch (requestCode) {
            case ConstantDefine.PERMISSION_CODE:
                Toast.makeText(getContext(), "권한이 허용되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case ConstantDefine.ACT_TAKE_PIC :
                //카메라로 찍은 사진을 받는다.
                if ((resultCode == RESULT_OK)) {

                    try {
                        File file = new File(mCurrentPhotoPath);
                        Bitmap rotatedBitmap = null;
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),
                                FileProvider.getUriForFile(getContext(),
                                        getContext().getPackageName() + ".fileprovider", file));

                        // 회전된 사진을 원래대로 돌려 표시한다.
                        if (bitmap != null) {
                            ExifInterface ei = new ExifInterface(mCurrentPhotoPath);
                            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                                    ExifInterface.ORIENTATION_UNDEFINED);
                            switch (orientation) {

                                case ExifInterface.ORIENTATION_ROTATE_90:
                                    rotatedBitmap = rotateImage(bitmap, 90);
                                    break;

                                case ExifInterface.ORIENTATION_ROTATE_180:
                                    rotatedBitmap = rotateImage(bitmap, 180);
                                    break;

                                case ExifInterface.ORIENTATION_ROTATE_270:
                                    rotatedBitmap = rotateImage(bitmap, 270);
                                    break;
                                case ExifInterface.ORIENTATION_NORMAL:
                                default:
                                    rotatedBitmap = bitmap;
                            }
                            Bitmap ip_img;
                            Image_processing image_processing=new Image_processing();
                            ip_img=image_processing.ImageProcessing(rotatedBitmap);
                            imageView.setImageBitmap(ip_img);
                            OCRThread ocrThread = new OCRThread(rotatedBitmap);
                            ocrThread.setDaemon(true);
                            ocrThread.start();

                            m_ocrTextView.setText(getResources().getString(R.string.LoadingMessage));
                        }
                    } catch (Exception e) {
                    }
                }
                break;
        }
    }











    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

    public void PermissionCheck() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getContext().checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED &&
                    getContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED &&
                    getContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                // 권한 없음
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        ConstantDefine.PERMISSION_CODE);
            } else {
                // 권한 있음
            }
        }
    }


    public void Tesseract() {

        mDataPath = getContext().getFilesDir() + "/tesseract/";


        String lang = "";
        for (String Language : mLanguageList) {
            checkFile(new File(mDataPath + "tessdata/"), Language);
            lang += Language + "+";
        }
        m_Tess = new TessBaseAPI();
        m_Tess.init(mDataPath, lang);
    }




    private void takePictureFromCameraIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity( getContext().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getContext(),
                        getContext().getApplicationContext().getPackageName()+".fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, ConstantDefine.ACT_TAKE_PIC);
            }
        }
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir;
        storageDir =  getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    //copy file to device
    private void copyFiles(String Language) {
        try {
            String filepath = mDataPath + "/tessdata/" + Language + ".traineddata";
            AssetManager assetManager = getContext().getAssets();
            InputStream instream = assetManager.open("tessdata/" + Language + ".traineddata");
            OutputStream outstream = new FileOutputStream(filepath);
            byte[] buffer = new byte[1024];
            int read;
            while ((read = instream.read(buffer)) != -1) {
                outstream.write(buffer, 0, read);
            }
            outstream.flush();
            outstream.close();
            instream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //check file on the device
    private void checkFile(File dir, String Language) {
        if (!dir.exists() && dir.mkdirs()) {
            copyFiles(Language);
        }
        if (dir.exists()) {
            String datafilepath = mDataPath + "tessdata/" + Language + ".traineddata";
            File datafile = new File(datafilepath);
            if (!datafile.exists()) {
                copyFiles(Language);
            }
        }
    }

    //region Thread
    public class OCRThread extends Thread {
        private Bitmap rotatedImage;

        OCRThread(Bitmap rotatedImage) {
            this.rotatedImage = rotatedImage;
            if (!ProgressFlag)
                m_objProgressCircle = ProgressCircleDialog.show(mContext, "", "", true);
            ProgressFlag = true;
        }

        @Override
        public void run() {
            super.run();
            // 사진의 글자를 인식해서 옮긴다
            String OCRresult = null;
            m_Tess.setImage(rotatedImage);
            OCRresult = m_Tess.getUTF8Text();

            Message message = Message.obtain();
            message.what = ConstantDefine.RESULT_OCR;
            message.obj = OCRresult;
            m_messageHandler.sendMessage(message);

        }
    }
    //endregion

    //region Handler
    public class MessageHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case ConstantDefine.RESULT_OCR:
                    TextView OCRTextView = getActivity().findViewById(R.id.tv_view);
                    OCRTextView.setText(String.valueOf(msg.obj)); //텍스트 변경

                    // 원형 프로그레스바 종료
                    if (m_objProgressCircle.isShowing() && m_objProgressCircle != null)
                        m_objProgressCircle.dismiss();
                    ProgressFlag = false;

                    Toast.makeText(mContext, getResources().getString(R.string.CompleteMessage), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
    //endregion

    public void Test() {
        image = BitmapFactory.decodeResource(getResources(), R.drawable.sampledata);
        mDataPath = getContext().getFilesDir() + "/tesseract/";


        String lang = "";
        for (String Language : mLanguageList) {
            checkFile(new File(mDataPath + "tessdata/"), Language);
            lang += Language + "+";
        }
        lang = lang.substring(0, lang.length() - 1);
        m_Tess = new TessBaseAPI();
        m_Tess.init(mDataPath, lang);
    }

    private void copyFiles2(String lang) {
        try {
            //location we want the file to be at
            String filepath = mDataPath + "/tessdata/" + lang + ".traineddata";

            //get access to AssetManager
            AssetManager assetManager = getContext().getAssets();

            //open byte streams for reading/writing
            InputStream instream = assetManager.open("tessdata/" + lang + ".traineddata");
            OutputStream outstream = new FileOutputStream(filepath);

            //copy the file to the location specified by filepath
            byte[] buffer = new byte[1024];
            int read;
            while ((read = instream.read(buffer)) != -1) {
                outstream.write(buffer, 0, read);
            }
            outstream.flush();
            outstream.close();
            instream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkFile2(File dir, String lang) {
        //directory does not exist, but we can successfully create it
        if (!dir.exists() && dir.mkdirs()) {
            copyFiles2(lang);
        }
        //The directory exists, but there is no data file in it
        if (dir.exists()) {
            String datafilepath = mDataPath + "/tessdata/" + lang + ".traineddata";
            File datafile = new File(datafilepath);
            if (!datafile.exists()) {
                copyFiles2(lang);
            }
        }
    }

    //Process an Image
    public void processImage(View view) {
        OCRThread ocrThread = new OCRThread(image);
        ocrThread.setDaemon(true);
        ocrThread.start();
    }



}