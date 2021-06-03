package com.example.sharedprefs_user_reg;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.util.Random;

public class LoginWorld extends AppCompatActivity {

    private TextView signup,forgot,text1,text2,txt_Login;

    private ProgressDialog dialog;

    private Handler handler;

    private SharedPreferences sp;

    private String SHARED_PREF="MySharedPref",sp_Login;

    private TextInputLayout layout1,layout2;

    private TextInputEditText etName,etPass;

    private ImageView img_login;

    private int countTimes;

    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_world);

        signup=findViewById(R.id.login_signup);
        handler=new Handler(Looper.getMainLooper());

        text1=findViewById(R.id.login_textNotty);
        text2=findViewById(R.id.login_textDescription);
        txt_Login=findViewById(R.id.login_textLogin);

        forgot=findViewById(R.id.login_forgot);

        etName=findViewById(R.id.login_textInputEditText1_Name);
        etPass=findViewById(R.id.login_textInputEditText2_Password);

        img_login=findViewById(R.id.login_circle);

        layout1=findViewById(R.id.login_textInputLayout1);
        layout2=findViewById(R.id.login_textInputLayout2);
        relativeLayout=findViewById(R.id.login_TopRelative);

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

        //Toast.makeText(this,"SCREEN SIZE:"+(double)screenInches,Toast.LENGTH_SHORT).show();

        if(screenInches<5)
        {
            text1.setTextSize(35);
            text2.setTextSize(15);

            txt_Login.setTextSize(20);

        }


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginWorld.this,SignUpWorld.class));
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginWorld.this,ForgotPassword.class));
            }
        });

        String s;

        sp=getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
        countTimes=sp.getInt("Counter",0);

        //Toast.makeText(LoginWorld.this,"COUNTER: "+countTimes,Toast.LENGTH_SHORT).show();

        img_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verifyNamePass();


            }
        });


    }

    private void gotoMainActivity() {

        startActivity(new Intent(LoginWorld.this,MainActivity.class));
    }

    @Override
    public void onBackPressed() {

        sp=getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
        if(!sp.getString("sp_HasUser","").equals("true"))
        {
            gotoMainActivity();
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Exit");
            builder.setMessage("Do you really want to exit?");
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
        }

    }

    private void goToAfterActivity() {

        startActivity(new Intent(LoginWorld.this,AfterLogin.class));

    }


    private void verifyNamePass() {

        String verify_Name,verify_Pass;

        sp=getSharedPreferences(SHARED_PREF,MODE_PRIVATE);

        verify_Name=sp.getString("sp_Name","");
        verify_Pass=sp.getString("sp_Pass","");

        if(etName.getText().toString().isEmpty())
        {
            layout1.setError("Please enter your name");
        }

        if(etPass.getText().toString().isEmpty())
        {
            layout2.setError("Please enter your password");
        }

        else if(!etName.getText().toString().isEmpty() && !etPass.getText().toString().isEmpty() &&etName.getText().toString().equals(verify_Name) && etPass.getText().toString().equals(verify_Pass))
        {
            //Toast.makeText(LoginWorld.this,"Name and Password matched!!",Toast.LENGTH_SHORT).show();
            layout1.setErrorEnabled(false);
            layout2.setErrorEnabled(false);

            //------------------
            sp_Login="true";
            SharedPreferences.Editor editor=sp.edit();
            editor.putString("has_LoggedIn",sp_Login);
            editor.commit();

            DynamicToast.make(LoginWorld.this, "Welcome, "+sp.getString("sp_Name","")+"!",getResources().getDrawable(R.drawable.ic_verify2),
                    getResources().getColor(R.color.tintColor), getResources().getColor(R.color.backgroundColor), 3000).show();

            goToAfterActivity();
        }

        else if(etName.getText().toString().equals(verify_Name) && !etPass.getText().toString().equals(verify_Pass))
        {
            Toast.makeText(LoginWorld.this,"Wrong Password!!",Toast.LENGTH_SHORT).show();
            layout2.setError("Wrong Password");
        }

        else
        {
            if(!etName.getText().toString().isEmpty() && !etPass.getText().toString().isEmpty() )
            {
                layout1.setErrorEnabled(false);
                layout2.setErrorEnabled(false);
            }
            Toast.makeText(LoginWorld.this,"Account doesn't exist!!",Toast.LENGTH_SHORT).show();
        }

        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layout1.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layout2.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

}