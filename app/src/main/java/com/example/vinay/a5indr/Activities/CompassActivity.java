package com.example.vinay.a5indr.Activities;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vinay.a5indr.R;

public class CompassActivity extends Activity implements SensorEventListener{
    private ImageView mCompassImage;
    private float mCurrentDegree=0f;
    private SensorManager mSensorManager;
    private TextView mHeading;
    private float[] mOrientation = new float[3];

    private float[] mGData = new float[3];
    private float[] mMData = new float[3];
    private float[] mR = new float[16];
    private float[] mI = new float[16];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compass_layout);
        mCompassImage=(ImageView)findViewById(R.id.imageViewCompass);
        mHeading=(TextView)findViewById(R.id.Heading);
        mSensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),SensorManager.SENSOR_DELAY_GAME);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        SensorManager.getRotationMatrix(mR, mI, mGData, mMData);
        SensorManager.getOrientation(mR, mOrientation);
        final float rad2deg = (float)(180.0f/Math.PI);
        Log.d("Compass", "yaw: " + (int)event.values[2]);
        float degree=Math.round(event.values[2]);
        mHeading.setText("Heading:"+Float.toString(degree)+"degrees");
        RotateAnimation ra=new  RotateAnimation(mCurrentDegree,degree,
                Animation.RELATIVE_TO_PARENT,0.5f,Animation.RELATIVE_TO_PARENT,0.5f);
        ra.setDuration(210);
        ra.setFillAfter(true);
        mCompassImage.startAnimation(ra);
        mCurrentDegree= degree;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
