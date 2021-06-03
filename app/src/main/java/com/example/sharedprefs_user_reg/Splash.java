package com.example.sharedprefs_user_reg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;

import java.util.Random;

import pl.droidsonroids.gif.GifImageView;


public class Splash extends AppCompatActivity{

    private ImageView img;
    private Animation anim;

    private Handler handler;

    private RoundedHorizontalProgressBar progressBar;

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler=new Handler(Looper.getMainLooper());
        tv=findViewById(R.id.splash_Text);

        anim=AnimationUtils.loadAnimation(this,R.anim.my_anim);

        tv.startAnimation(anim);

        /*img=findViewById(R.id.splash_imageView);

        anim= AnimationUtils.loadAnimation(this,R.anim.my_anim);

        progressBar=findViewById(R.id.splash_progressBar1);

        img.startAnimation(anim);

        progressBar.setMax(100);

        progressBar.animateProgress(5000,0,100);*/

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this,MainActivity.class));
                finish();
            }
        },new Random().nextInt(2000)+2000);

    }

}