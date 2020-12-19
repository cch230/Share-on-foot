package com.example.shareonfoot;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

//스와이프 잠금 해제 관련
//import me.imid.swipebacklayout.lib.SwipeBackLayout;
//import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class LockScreenActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor stepCountSensor;
    TextView tvStepCount;
    TextView tvTodayPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        tvStepCount = (TextView)findViewById(R.id.tvStepCount);
        tvTodayPoint = (TextView) findViewById(R.id.tvTodayPoint);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        stepCountSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(stepCountSensor == null) {
            Toast.makeText(this, "No Step Detect Sensor", Toast.LENGTH_SHORT).show();
        }

//        //스와이프 잠금 해제
//        SwipeBackLayout swipeBackLayout = getSwipeBackLayout();
//
//        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
//
//        swipeBackLayout.addSwipeListener(new SwipeBackLayout.SwipeListener() {
//            @Override
//            public void onScrollStateChange(int state, float scrollPercent) {
//                // 스크롤 될 때
//            }
//
//            @Override
//            public void onEdgeTouch(int edgeFlag) {
//                // 설정된 모서리를 터치했을 때
//            }
//
//            @Override
//            public void onScrollOverThreshold() {
//                // 창이 닫힐 정도로 swipe 되었을 때
//                finish();
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, stepCountSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        sensorManager.unregisterListener(this);
//    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {

            // 걸음 수
            int todayCount = (int) event.values[0];
            tvStepCount.setText("오늘은 " + String.valueOf(todayCount)+" 걸음을 걸었어요!");

            //포인트 환산
            int todayPoint = todayCount / 100;
            tvTodayPoint.setText((todayPoint) + " p");

        }



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}