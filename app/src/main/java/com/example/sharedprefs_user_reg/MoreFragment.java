package com.example.sharedprefs_user_reg;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.hanks.htextview.base.HTextView;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.util.ArrayList;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

public class MoreFragment extends Fragment {

    private ImageView logout,delete,about;

    private SharedPreferences sp;

    private String SHARED_PREF="MySharedPref";

    private ProgressDialog dialog;

    Handler handler,handler1;

    HTextView hTextView;

    private HTextView textViewScale;
    int delay = 2000; //milliseconds
    ArrayList<String> arrMessages = new ArrayList<>();
    int position=0;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        logout=view.findViewById(R.id.more_logout);
        delete=view.findViewById(R.id.more_delete);
        about=view.findViewById(R.id.more_about);

        sp=getActivity().getSharedPreferences(SHARED_PREF,MODE_PRIVATE);

        handler=new Handler(Looper.getMainLooper());
        handler1=new Handler(Looper.getMainLooper());

        dialog=new ProgressDialog(getActivity());
        dialog.show();
        dialog.setContentView(R.layout.dots_progress);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        },new Random().nextInt(1000));

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logoutUser();

                DynamicToast.make(getActivity(), "You have been logged out!!!", getResources().getDrawable(R.drawable.ic_outline_error_outline_24),
                        getResources().getColor(R.color.tintColor), getResources().getColor(R.color.backgroundColor), 3000).show();

            }
        });

        String str_Name;
        str_Name=sp.getString("sp_Name","");
        int unicode=0x2764;
        String emoji=getEmoji(unicode);


        /* Some Sample Messages for Animation */
        arrMessages.add(emoji+" Hello, "+str_Name+"! "+emoji);
        arrMessages.add(emoji+" Welcome to ColorNote App "+emoji);
        arrMessages.add(emoji+" By Anupam Basak "+emoji);
        arrMessages.add(emoji+" Thank You for using it "+emoji);
        arrMessages.add(emoji+" Support Us "+emoji);

        /* Initialize HTextView */
        textViewScale= view.findViewById(R.id.more_text);

        /* First Message */
        textViewScale.animateText(arrMessages.get(position));
        position++;

        /* Change Messages every 2 Seconds */
        handler = new Handler();
        handler.postDelayed(new Runnable(){
            public void run(){

                handler.postDelayed(this, delay);
                if(position>=arrMessages.size())
                    position=0;
                textViewScale.animateText(arrMessages.get(position));
                position++;
            }
        }, delay);


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setTitle("Warning");
                builder.setMessage("This will remove your account permanently!");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        deleteUser();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });

                builder.show();
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aboutUser();
            }
        });

    }

    private String getEmoji(int unicode) {

        return new String(Character.toChars(unicode));
    }

    private void logoutUser() {

        sp=getActivity().getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();

        editor.putString("sp_HasUser","true");
        editor.putString("has_LoggedIn","false");
        editor.commit();

        getActivity().startActivity(new Intent(getActivity(),LoginWorld.class));
        getActivity().finish();


    }

    private void deleteUser() {

        sp=getActivity().getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();

        editor.remove("sp_Name");
        editor.remove("sp_Email");
        editor.remove("sp_Pass");
        editor.remove("sp_CPass");
        editor.remove("sp_HasUser");
        editor.remove("has_LoggedIn");

        editor.remove("Title1");
        editor.remove("Des1");
        editor.remove("Title2");
        editor.remove("Des2");
        editor.remove("Title3");
        editor.remove("Des3");
        editor.remove("Title4");
        editor.remove("Des4");
        editor.remove("Title5");
        editor.remove("Des5");
        editor.remove("Title6");
        editor.remove("Des6");
        editor.remove("Title7");
        editor.remove("Des7");
        editor.remove("Title8");
        editor.remove("Des8");
        editor.remove("Title9");
        editor.remove("Des9");
        editor.remove("Title10");
        editor.remove("Des10");

        editor.putInt("Count",0);

        editor.putInt("Counter",0);

        editor.commit();

        //Toast.makeText(getActivity(),"User has been removed!",Toast.LENGTH_SHORT).show();

        DynamicToast.make(getActivity(), "User has been removed!!!",getResources().getDrawable(R.drawable.ic_baseline_remove_circle_outline_24),
                getResources().getColor(R.color.tintColor), getResources().getColor(R.color.backgroundColor), 3000).show();

        getActivity().startActivity(new Intent(getActivity(),MainActivity.class));

    }

    private void aboutUser() {

        getActivity().startActivity(new Intent(getActivity(),AboutUs.class));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_more, container, false);
    }
}