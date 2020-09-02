package com.rupesh.baji.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.rupesh.baji.R;

public class SplashScreen extends AppCompatActivity {

    Animation topAnim, bottomAnim, bottomRotate, textDrop;
    ImageView imgTri, imgCircle, imgSquare, imgX;
    TextView txtInfo, txtSubInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        bottomRotate = AnimationUtils.loadAnimation(this, R.anim.bottom_animation_rotate);
        textDrop = AnimationUtils.loadAnimation(this, R.anim.text_drop);

        imgTri= findViewById(R.id.imageView_triangle);
        imgCircle = findViewById(R.id.imageView_circle);
        imgSquare = findViewById(R.id.imageView_square);
        imgX = findViewById(R.id.imageView_x);

        txtInfo = findViewById(R.id.tv_info);
        txtSubInfo = findViewById(R.id.tv_subinfo);

        imgTri.setAnimation(topAnim);
        imgX.setAnimation(bottomRotate);

        txtInfo.setAnimation(textDrop);
        txtSubInfo.setAnimation(textDrop);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this,  com.rupesh.baji.activities.Login.class));
                finish();
            }
        }, 5000);
    }
}
