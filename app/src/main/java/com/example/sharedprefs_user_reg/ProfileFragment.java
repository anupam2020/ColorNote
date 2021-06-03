package com.example.sharedprefs_user_reg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {

    private TextView tv,tV_Email;

    private String str_Name,str_Email,SHARED_PREF="MySharedPref";

    private SharedPreferences sp;

    private ImageView changePass;

    private ProgressDialog dialog;

    private Handler handler;

    private FrameLayout layout;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv=view.findViewById(R.id.afterLogin_textView);
        tV_Email=view.findViewById(R.id.afterLogin_email);
        changePass=view.findViewById(R.id.profile_changePass);

        sp=getActivity().getSharedPreferences(SHARED_PREF,MODE_PRIVATE);

        str_Name=sp.getString("sp_Name","");
        str_Email=sp.getString("sp_Email","");

        SharedPreferences.Editor editor=sp.edit();
        editor.putString("has_LoggedIn","true");
        editor.putString("sp_HasUser","true");
        editor.commit();

        tv.setText(str_Name);
        tV_Email.setText(str_Email);

        handler=new Handler(Looper.getMainLooper());
        layout=view.findViewById(R.id.frameLayout);

        dialog=new ProgressDialog(getActivity());
        dialog.show();
        dialog.setContentView(R.layout.dots_progress);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        },new Random().nextInt(3000));


        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tV_Email.clearFocus();

            }
        });

        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changePassword();
            }
        });

    }

    private void changePassword() {

        getActivity().startActivity(new Intent(getActivity(),ForgotPassword.class));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

}