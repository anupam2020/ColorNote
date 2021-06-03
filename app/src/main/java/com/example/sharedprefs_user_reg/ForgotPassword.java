package com.example.sharedprefs_user_reg;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.util.Random;
import java.util.regex.Pattern;

public class ForgotPassword extends AppCompatActivity {

    private TextInputEditText pass,confirmPass;

    private TextInputLayout layout1,layout2;

    private TextView tvNot,tvChange;

    private final String SHARED_PREFS="MySharedPref";

    private SharedPreferences sp;

    private ImageView save;

    private Pattern pat_password=Pattern.compile("^"+
            "(.{6,})"+
            "$");

    private ProgressDialog dialog;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        tvNot=findViewById(R.id.forgot_textNotty);
        pass=findViewById(R.id.forgot_textInputEditText1_Password);
        confirmPass=findViewById(R.id.forgot_textInputEditText2_ConfirmPassword);
        tvChange=findViewById(R.id.forgot_changePass);

        layout1=findViewById(R.id.forgot_textInputLayout1);
        layout2=findViewById(R.id.forgot_textInputLayout2);

        save=findViewById(R.id.forgot_save);

        sp=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);

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
        },new Random().nextInt(1500));

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        double wi=(double)width/(double)dm.xdpi;
        double hi=(double)height/(double)dm.ydpi;
        double x = Math.pow(wi,2);
        double y = Math.pow(hi,2);
        double screenInches = Math.sqrt(x+y);

        //Toast.makeText(this,"SCREEN SIZE:"+(double)screenInches, Toast.LENGTH_SHORT).show();

        if(screenInches<5)
        {
            tvNot.setTextSize(35);

            tvChange.setTextSize(20);
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkPassword();
            }
        });

    }

    private void checkPassword() {

        if(pass.getText().toString().isEmpty())
        {
            layout1.setError("Password cannot be empty!");
        }
        else
        {
            //
        }


        if(confirmPass.getText().toString().isEmpty())
        {
            layout2.setError("Password cannot be empty!");
        }
        else
        {
            //layout4.setErrorEnabled(false);
        }

        pass.addTextChangedListener(new TextWatcher() {
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

        confirmPass.addTextChangedListener(new TextWatcher() {
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

        if(!pass.getText().toString().isEmpty() && !pat_password.matcher(pass.getText().toString()).matches())
        {
            layout1.setError("Password must be min. 6 digits");
        }


        if(!confirmPass.getText().toString().isEmpty() && !pat_password.matcher(confirmPass.getText().toString()).matches())
        {
            layout2.setError("Password must be min. 6 digits");
        }


        if(!pass.getText().toString().isEmpty() && pat_password.matcher(pass.getText().toString()).matches() && !pass.getText().toString().equals(confirmPass.getText().toString()))
        {
            layout1.setError("Passwords are not matching");
        }

        if(!confirmPass.getText().toString().isEmpty() && pat_password.matcher(confirmPass.getText().toString()).matches() && !confirmPass.getText().toString().equals(pass.getText().toString()))
        {
            layout2.setError("Passwords are not matching");
        }

        if(!pass.getText().toString().isEmpty() && !confirmPass.getText().toString().isEmpty() && pat_password.matcher(pass.getText().toString()).matches() && pat_password.matcher(confirmPass.getText().toString()).matches() && pass.getText().toString().equals(confirmPass.getText().toString()))
        {
            layout1.setErrorEnabled(false);
            layout2.setErrorEnabled(false);

            //Toast.makeText(ForgotPassword.this,"Password was changed!!",Toast.LENGTH_SHORT).show();

            DynamicToast.make(ForgotPassword.this, "Password was successfully changed!",getResources().getDrawable(R.drawable.lock_outline),
                    getResources().getColor(R.color.tintColor), getResources().getColor(R.color.backgroundColor), 3000).show();

            String sp_pass,sp_cPass;

            sp_pass=pass.getText().toString();
            sp_cPass=confirmPass.getText().toString();

            SharedPreferences.Editor editor=sp.edit();

            editor.putString("sp_Pass",sp_pass);
            editor.putString("sp_CPass",sp_cPass);
            editor.commit();

            logoutUser();
        }

    }

    private void logoutUser() {

        sp=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();

        editor.putString("sp_HasUser","true");
        editor.putString("has_LoggedIn","false");
        editor.commit();

        startActivity(new Intent(this,LoginWorld.class));
        finish();


    }
}