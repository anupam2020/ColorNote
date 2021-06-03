package com.example.sharedprefs_user_reg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.Random;

public class AfterLogin extends AppCompatActivity {

    private SharedPreferences sp;

    private String SHARED_PREF="MySharedPref";

    private ProgressDialog dialog;

    private Handler handler;

    private ChipNavigationBar bar;

    MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);

        bar=findViewById(R.id.chipNavBar);

        handler=new Handler(Looper.getMainLooper());

        sp=getSharedPreferences(SHARED_PREF,MODE_PRIVATE);

        getSupportFragmentManager().beginTransaction().replace(R.id.relativeReplace,new HomeFragment()).commit();
        bar.setItemSelected(R.id.home,true);
        bar.setItemEnabled(R.id.home,true);

        dialog=new ProgressDialog(this);
        dialog.show();
        dialog.setContentView(R.layout.dots_progress);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        bar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {

                switch (i)
                {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.relativeReplace,new HomeFragment()).commit();
                        bar.setItemSelected(R.id.home,true);
                        break;
                    case R.id.settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.relativeReplace,new ProfileFragment()).commit();
                        bar.setItemSelected(R.id.settings,true);
                        break;
                    case R.id.more:
                        getSupportFragmentManager().beginTransaction().replace(R.id.relativeReplace,new MoreFragment()).commit();
                        bar.setItemSelected(R.id.more,true);
                        break;
                }

            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        },new Random().nextInt(3000));


    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Warning");
        builder.setMessage("Please select any one!");

        builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                logoutUser();
            }
        });

        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finishAffinity();
            }
        });

        builder.show();

    }


    private void logoutUser() {

        sp=getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();

        editor.putString("sp_HasUser","true");
        editor.putString("has_LoggedIn","false");
        editor.commit();


        startActivity(new Intent(AfterLogin.this,LoginWorld.class));
        this.finish();

    }
}