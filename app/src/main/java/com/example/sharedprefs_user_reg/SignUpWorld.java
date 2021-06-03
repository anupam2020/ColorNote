package com.example.sharedprefs_user_reg;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

public class SignUpWorld extends AppCompatActivity {

    private ProgressDialog dialog;
    private Handler handler,handler1;
    private TextView login,tvNot,tvReg;

    private static String hasUser="null";

    private TextInputEditText name,email,pass,confirmPass;

    private TextInputLayout layout1,layout2,layout3,layout4;

    private final String SHARED_PREFS="MySharedPref";

    private SharedPreferences sp;

    private ImageView signup,img;

    private LottieAnimationView lottie;

    private RelativeLayout relativeLayout;

    private Pattern pat_password=Pattern.compile("^"+
                                            "(.{6,})"+
                                            "$");

    private Pattern pat_name=Pattern.compile("^"+
                                        "(?=.*[a-z])"+
                                        "(?=.*[A-Z])"+
                                        "(.{4,})"+
                                        "$");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_world);

        tvReg=findViewById(R.id.login_textLogin);
        img=findViewById(R.id.signup_image);

        lottie=findViewById(R.id.lottieAnimation);
        handler=new Handler(Looper.getMainLooper());
        handler1=new Handler(Looper.getMainLooper());
        login=findViewById(R.id.signup_login);
        tvNot=findViewById(R.id.login_textLogin1);

        relativeLayout=findViewById(R.id.signup_relativeLayout1);

        name=findViewById(R.id.signup_textInputEditText1_Name);
        email=findViewById(R.id.signup_textInputEditText2_Email);
        pass=findViewById(R.id.signup_textInputEditText3_Password);
        confirmPass=findViewById(R.id.signup_textInputEditText4_ConfirmPassword);

        layout1=findViewById(R.id.signup_textInputLayout1);
        layout2=findViewById(R.id.signup_textInputLayout2);
        layout3=findViewById(R.id.signup_textInputLayout3);
        layout4=findViewById(R.id.signup_textInputLayout4);

        signup=findViewById(R.id.rectangle);

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

         if(screenInches<5) {
            lottie.setVisibility(View.GONE);

            /*RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin=10;

            tvNot.setLayoutParams(params);

            tvNot.requestLayout();*/

            tvNot.setTextSize(35);

            tvReg.setTextSize(20);

            img.setImageResource(R.drawable.ic_signup_low_size);

        }

        /*else if(screenInches>=5 && screenInches<=5.5)
        {
            RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin=20;

            tvNot.setLayoutParams(params);

            tvNot.requestLayout();

        }

        else {
             RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
             params.leftMargin=30;

             tvNot.setLayoutParams(params);

             tvNot.requestLayout();
         }*/

        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {

                sp=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                //Toast.makeText(SignUpWorld.this,"HASUSER: "+sp.getString("sp_HasUser",""),Toast.LENGTH_SHORT).show();

                if(sp.getString("sp_HasUser","").equals("true"))
                {
                    AlertDialog.Builder builder=new AlertDialog.Builder(SignUpWorld.this);
                    builder.setTitle("Warning");
                    builder.setMessage("Note: If you create more than one account, your previous account and datas will be deleted!!!");

                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    builder.show();
                }

            }
        },3000);



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validateUser();

                //storeUserData();
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpWorld.this,LoginWorld.class));
            }
        });

    }

    private void storeUserData() {

        String sp_name,sp_email,sp_pass,sp_cPass;
        int countTimes=1;

        sp_name=name.getText().toString();
        sp_email=email.getText().toString();
        sp_pass=pass.getText().toString();
        sp_cPass=confirmPass.getText().toString();

        sp=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);

        SharedPreferences.Editor editor=sp.edit();

        editor.putString("sp_Name",sp_name);
        editor.putString("sp_Email",sp_email);
        editor.putString("sp_Pass",sp_pass);
        editor.putString("sp_CPass",sp_cPass);
        editor.putString("sp_HasUser",hasUser);
        editor.putInt("Counter",countTimes);

        editor.commit();



    }

    private void validateUser() {

        if(name.getText().toString().isEmpty())
        {
            layout1.setError("Name cannot be empty!");
        }
        else
        {
            //
        }


        if(email.getText().toString().isEmpty())
        {
            layout2.setError("Email cannot be empty!");
        }
        else
        {
            //
        }


        if(pass.getText().toString().isEmpty())
        {
            layout3.setError("Password cannot be empty!");
        }
        else
        {
            //
        }


        if(confirmPass.getText().toString().isEmpty())
        {
            layout4.setError("Password cannot be empty!");
        }
        else
        {
            //layout4.setErrorEnabled(false);
        }

        name.addTextChangedListener(new TextWatcher() {
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

        email.addTextChangedListener(new TextWatcher() {
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

        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layout3.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        confirmPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layout4.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if(!name.getText().toString().isEmpty() && !pat_name.matcher(name.getText().toString()).matches())
        {
            layout1.setError("Plase enter a valid name");
        }

        if(!email.getText().toString().isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches())
        {
            layout2.setError("Please enter a valid Email");
        }

        if(!pass.getText().toString().isEmpty() && !pat_password.matcher(pass.getText().toString()).matches())
        {
            layout3.setError("Password must be min. 6 characters");
        }


        if(!confirmPass.getText().toString().isEmpty() && !pat_password.matcher(confirmPass.getText().toString()).matches())
        {
            layout4.setError("Password must be min. 6 characters");
        }


        if(!pass.getText().toString().isEmpty() && pat_password.matcher(pass.getText().toString()).matches() && !pass.getText().toString().equals(confirmPass.getText().toString()))
        {
            layout3.setError("Passwords are not matching");
        }

        if(!confirmPass.getText().toString().isEmpty() && pat_password.matcher(confirmPass.getText().toString()).matches() && !confirmPass.getText().toString().equals(pass.getText().toString()))
        {
            layout4.setError("Passwords are not matching");
        }

        if(!name.getText().toString().isEmpty() && pat_name.matcher(name.getText().toString()).matches() && !pass.getText().toString().isEmpty() && !confirmPass.getText().toString().isEmpty() && pat_password.matcher(pass.getText().toString()).matches() && pat_password.matcher(confirmPass.getText().toString()).matches() && pass.getText().toString().equals(confirmPass.getText().toString()) && !name.getText().toString().isEmpty() && !email.getText().toString().isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches() )
        {
            layout3.setErrorEnabled(false);
            layout4.setErrorEnabled(false);

            //Toast.makeText(SignUpWorld.this,"Good to go!",Toast.LENGTH_SHORT).show();


            DynamicToast.make(SignUpWorld.this, "Registration Successful!!!", getResources().getDrawable(R.drawable.ic_outline_check_circle_24),
                    getResources().getColor(R.color.tintColor), getResources().getColor(R.color.backgroundColor), 3000).show();

            sp=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);

            hasUser="true";

            storeUserData();

            startActivity(new Intent(SignUpWorld.this,LoginWorld.class));
        }

    }

    private void gotoMainActivity() {

        startActivity(new Intent(SignUpWorld.this,MainActivity.class));
    }

    @Override
    public void onBackPressed() {

        sp=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
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
}