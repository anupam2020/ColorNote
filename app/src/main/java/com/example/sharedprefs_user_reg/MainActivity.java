package com.example.sharedprefs_user_reg;

import androidx.annotation.Dimension;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;


import com.airbnb.lottie.LottieAnimationView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView signup,login;
    private ProgressDialog dialog;
    private Handler handler;

    private String str="MySharedPref";

    private SharedPreferences sp;

    private LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signup=findViewById(R.id.mainSignUp);
        login=findViewById(R.id.mainLogin);
        lottie=findViewById(R.id.main_lottieAnimation);

        checkUser();

        handler=new Handler(Looper.getMainLooper());

        dialog=new ProgressDialog(this);
        dialog.show();
        dialog.setContentView(R.layout.dots_progress);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        },new Random().nextInt(3000));

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        double wi=(double)width/(double)dm.xdpi;
        double hi=(double)height/(double)dm.ydpi;
        double x = Math.pow(wi,2);
        double y = Math.pow(hi,2);
        double screenInches = Math.sqrt(x+y);

        if(screenInches<5.0)
        {
            signup.getLayoutParams().width = 700;
            signup.getLayoutParams().height = 250;

            login.getLayoutParams().width = 700;
            login.getLayoutParams().height = 250;

            lottie.getLayoutParams().width = 500;
            lottie.getLayoutParams().height = 500;
        }

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SignUpWorld.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginWorld.class));
            }
        });

    }

    private void checkUser() {

        String userSIGNUP,userLOGIN;
        sp=getSharedPreferences(str,MODE_PRIVATE);

        userSIGNUP=sp.getString("sp_HasUser","");
        userLOGIN=sp.getString("has_LoggedIn","");

        //Toast.makeText(MainActivity.this,"HasSIGNUP: "+userSIGNUP,Toast.LENGTH_SHORT).show();
        //Toast.makeText(MainActivity.this,"HasLOGIN: "+userLOGIN,Toast.LENGTH_SHORT).show();

        if(userLOGIN.equals("true"))
        {
            startActivity(new Intent(MainActivity.this,AfterLogin.class));
            this.finish();
        }
        else
        {
            if(userSIGNUP.equals("true"))
            {
                startActivity(new Intent(MainActivity.this,LoginWorld.class));
                this.finish();
            }
        }

    }


    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure want to exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finishAffinity();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        //AlertDialog alertDialog=builder.create();
        builder.show();

        //exitFromApp();
    }

    /*private void exitFromApp() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        startActivity(intent);
    }*/
}