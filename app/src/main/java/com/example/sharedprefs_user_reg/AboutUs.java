package com.example.sharedprefs_user_reg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class AboutUs extends AppCompatActivity {

    private TextView tv2,tv3;
    private RelativeLayout root_layout ;

    private ProgressDialog dialog;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        tv2=findViewById(R.id.about_contact);
        tv3=findViewById(R.id.about_marquee);

        root_layout=findViewById(R.id.root_layout);

        handler=new Handler(Looper.getMainLooper());

        dialog=new ProgressDialog(this);
        dialog.show();
        dialog.setContentView(R.layout.dots_progress);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        root_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv2.clearFocus();

            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        },new Random().nextInt(3000));

        tv3.setSelected(true);

    }
}