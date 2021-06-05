package com.example.sharedprefs_user_reg;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    private ProgressDialog dialog;

    private Handler handler;

    private RelativeLayout layout1,layout2,layout3,layout4,layout5,layout6,layout7,layout8,layout9,layout10,layout11;

    private EditText title1,title2,title3,title4,title5,title6,title7,title8,title9,title10;

    private EditText des1,des2,des3,des4,des5,des6,des7,des8,des9,des10;

    private SharedPreferences sp;

    private final String SHARED_PREFS="MySharedPref";

    private static int count=0,warning=0;

    private ImageView note,tick1,tick2,tick3,tick4,tick5,tick6,tick7,tick8,tick9,tick10;

    private String title,des;

    private TextView tv;

    private Animation tick_anim;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layout1=view.findViewById(R.id.home_relative1);
        layout2=view.findViewById(R.id.home_relative2);
        layout3=view.findViewById(R.id.home_relative3);
        layout4=view.findViewById(R.id.home_relative4);
        layout5=view.findViewById(R.id.home_relative5);
        layout6=view.findViewById(R.id.home_relative6);
        layout7=view.findViewById(R.id.home_relative7);
        layout8=view.findViewById(R.id.home_relative8);
        layout9=view.findViewById(R.id.home_relative9);
        layout10=view.findViewById(R.id.home_relative10);
        layout11=view.findViewById(R.id.home_relative11);

        tick1=view.findViewById(R.id.iv1_Tick);
        tick2=view.findViewById(R.id.iv2_Tick);
        tick3=view.findViewById(R.id.iv3_Tick);
        tick4=view.findViewById(R.id.iv4_Tick);
        tick5=view.findViewById(R.id.iv5_Tick);
        tick6=view.findViewById(R.id.iv6_Tick);
        tick7=view.findViewById(R.id.iv7_Tick);
        tick8=view.findViewById(R.id.iv8_Tick);
        tick9=view.findViewById(R.id.iv9_Tick);
        tick10=view.findViewById(R.id.iv10_Tick);

        title1=view.findViewById(R.id.et1_Title);
        title2=view.findViewById(R.id.et2_Title);
        title3=view.findViewById(R.id.et3_Title);
        title4=view.findViewById(R.id.et4_Title);
        title5=view.findViewById(R.id.et5_Title);
        title6=view.findViewById(R.id.et6_Title);
        title7=view.findViewById(R.id.et7_Title);
        title8=view.findViewById(R.id.et8_Title);
        title9=view.findViewById(R.id.et9_Title);
        title10=view.findViewById(R.id.et10_Title);

        des1=view.findViewById(R.id.et1_Description);
        des2=view.findViewById(R.id.et2_Description);
        des3=view.findViewById(R.id.et3_Description);
        des4=view.findViewById(R.id.et4_Description);
        des5=view.findViewById(R.id.et5_Description);
        des6=view.findViewById(R.id.et6_Description);
        des7=view.findViewById(R.id.et7_Description);
        des8=view.findViewById(R.id.et8_Description);
        des9=view.findViewById(R.id.et9_Description);
        des10=view.findViewById(R.id.et10_Description);

        note= view.findViewById(R.id.iv_addNotes);

        tick_anim= AnimationUtils.loadAnimation(getActivity(),R.anim.zoom_out);

        tv=view.findViewById(R.id.tv_marquee);

        SpannableString spannableString = new SpannableString(tv.getText().toString());

        int nightModeFlags =
                getActivity().getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:

                ForegroundColorSpan white = new ForegroundColorSpan(Color.WHITE);
                spannableString.setSpan(white,
                        0, 66, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                tv.setText(spannableString);


                title1.setTextColor(Color.parseColor("#FFFFFF"));
                title2.setTextColor(Color.parseColor("#FFFFFF"));
                title3.setTextColor(Color.parseColor("#FFFFFF"));
                title4.setTextColor(Color.parseColor("#FFFFFF"));
                title5.setTextColor(Color.parseColor("#FFFFFF"));
                title6.setTextColor(Color.parseColor("#FFFFFF"));
                title7.setTextColor(Color.parseColor("#FFFFFF"));
                title8.setTextColor(Color.parseColor("#FFFFFF"));
                title9.setTextColor(Color.parseColor("#FFFFFF"));
                title10.setTextColor(Color.parseColor("#FFFFFF"));

                des1.setTextColor(Color.parseColor("#FFFFFF"));
                des2.setTextColor(Color.parseColor("#FFFFFF"));
                des3.setTextColor(Color.parseColor("#FFFFFF"));
                des4.setTextColor(Color.parseColor("#FFFFFF"));
                des5.setTextColor(Color.parseColor("#FFFFFF"));
                des6.setTextColor(Color.parseColor("#FFFFFF"));
                des7.setTextColor(Color.parseColor("#FFFFFF"));
                des8.setTextColor(Color.parseColor("#FFFFFF"));
                des9.setTextColor(Color.parseColor("#FFFFFF"));
                des10.setTextColor(Color.parseColor("#FFFFFF"));

                break;
        }

        ForegroundColorSpan red = new ForegroundColorSpan(Color.RED);

        spannableString.setSpan(red,
                66, tv.getText().toString().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tv.setText(spannableString);

        tv.setSelected(true);

        handler=new Handler(Looper.getMainLooper());

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

        sp=getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        count=sp.getInt("Count",count);

        //Toast.makeText(getActivity(),"COUNT: "+count,Toast.LENGTH_SHORT).show();

        if(count==1)
        {
            layout1.setVisibility(View.VISIBLE);

            if(!sp.getString("Title1","").isEmpty()) {
                tick1.setAlpha((float) 1.0);
            }


            title=sp.getString("Title1","");
            des=sp.getString("Des1","");

            title1.setText(title);
            des1.setText(des);

        }

        else if(count==2)
        {
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.VISIBLE);

            if(!sp.getString("Title1","").isEmpty()) {
                tick1.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title2","").isEmpty()) {
                tick2.setAlpha((float) 1.0);
            }

            title=sp.getString("Title1","");
            des=sp.getString("Des1","");

            title1.setText(title);
            des1.setText(des);

            title=sp.getString("Title2","");
            des=sp.getString("Des2","");

            title2.setText(title);
            des2.setText(des);

        }

        else if(count==3)
        {
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.VISIBLE);
            layout3.setVisibility(View.VISIBLE);

            if(!sp.getString("Title1","").isEmpty()) {
                tick1.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title2","").isEmpty()) {
                tick2.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title3","").isEmpty()) {
                tick3.setAlpha((float) 1.0);
            }

            title=sp.getString("Title1","");
            des=sp.getString("Des1","");

            title1.setText(title);
            des1.setText(des);

            title=sp.getString("Title2","");
            des=sp.getString("Des2","");

            title2.setText(title);
            des2.setText(des);

            title=sp.getString("Title3","");
            des=sp.getString("Des3","");

            title3.setText(title);
            des3.setText(des);

        }
        else if(count==4)
        {
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.VISIBLE);
            layout3.setVisibility(View.VISIBLE);
            layout4.setVisibility(View.VISIBLE);

            if(!sp.getString("Title1","").isEmpty()) {
                tick1.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title2","").isEmpty()) {
                tick2.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title3","").isEmpty()) {
                tick3.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title4","").isEmpty()) {
                tick4.setAlpha((float) 1.0);
            }

            title=sp.getString("Title1","");
            des=sp.getString("Des1","");

            title1.setText(title);
            des1.setText(des);

            title=sp.getString("Title2","");
            des=sp.getString("Des2","");

            title2.setText(title);
            des2.setText(des);

            title=sp.getString("Title3","");
            des=sp.getString("Des3","");

            title3.setText(title);
            des3.setText(des);

            title=sp.getString("Title4","");
            des=sp.getString("Des4","");

            title4.setText(title);
            des4.setText(des);

        }
        else if(count==5)
        {
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.VISIBLE);
            layout3.setVisibility(View.VISIBLE);
            layout4.setVisibility(View.VISIBLE);
            layout5.setVisibility(View.VISIBLE);

            if(!sp.getString("Title1","").isEmpty()) {
                tick1.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title2","").isEmpty()) {
                tick2.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title3","").isEmpty()) {
                tick3.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title4","").isEmpty()) {
                tick4.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title5","").isEmpty()) {
                tick5.setAlpha((float) 1.0);
            }


            title=sp.getString("Title1","");
            des=sp.getString("Des1","");

            title1.setText(title);
            des1.setText(des);

            title=sp.getString("Title2","");
            des=sp.getString("Des2","");

            title2.setText(title);
            des2.setText(des);

            title=sp.getString("Title3","");
            des=sp.getString("Des3","");

            title3.setText(title);
            des3.setText(des);

            title=sp.getString("Title4","");
            des=sp.getString("Des4","");

            title4.setText(title);
            des4.setText(des);

            title=sp.getString("Title5","");
            des=sp.getString("Des5","");

            title5.setText(title);
            des5.setText(des);

        }
        else if(count==6)
        {
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.VISIBLE);
            layout3.setVisibility(View.VISIBLE);
            layout4.setVisibility(View.VISIBLE);
            layout5.setVisibility(View.VISIBLE);
            layout6.setVisibility(View.VISIBLE);

            if(!sp.getString("Title1","").isEmpty()) {
                tick1.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title2","").isEmpty()) {
                tick2.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title3","").isEmpty()) {
                tick3.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title4","").isEmpty()) {
                tick4.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title5","").isEmpty()) {
                tick5.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title6","").isEmpty()) {
                tick6.setAlpha((float) 1.0);
            }


            title=sp.getString("Title1","");
            des=sp.getString("Des1","");

            title1.setText(title);
            des1.setText(des);

            title=sp.getString("Title2","");
            des=sp.getString("Des2","");

            title2.setText(title);
            des2.setText(des);

            title=sp.getString("Title3","");
            des=sp.getString("Des3","");

            title3.setText(title);
            des3.setText(des);

            title=sp.getString("Title4","");
            des=sp.getString("Des4","");

            title4.setText(title);
            des4.setText(des);

            title=sp.getString("Title5","");
            des=sp.getString("Des5","");

            title5.setText(title);
            des5.setText(des);

            title=sp.getString("Title6","");
            des=sp.getString("Des6","");

            title6.setText(title);
            des6.setText(des);

            title=sp.getString("Title7","");
            des=sp.getString("Des7","");

            title7.setText(title);
            des7.setText(des);

            title=sp.getString("Title8","");
            des=sp.getString("Des8","");

            title8.setText(title);
            des8.setText(des);

            title=sp.getString("Title9","");
            des=sp.getString("Des9","");

            title9.setText(title);
            des9.setText(des);

            title=sp.getString("Title10","");
            des=sp.getString("Des10","");

            title10.setText(title);
            des10.setText(des);

        }

        else if(count==7)
        {
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.VISIBLE);
            layout3.setVisibility(View.VISIBLE);
            layout4.setVisibility(View.VISIBLE);
            layout5.setVisibility(View.VISIBLE);
            layout6.setVisibility(View.VISIBLE);
            layout7.setVisibility(View.VISIBLE);

            if(!sp.getString("Title1","").isEmpty()) {
                tick1.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title2","").isEmpty()) {
                tick2.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title3","").isEmpty()) {
                tick3.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title4","").isEmpty()) {
                tick4.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title5","").isEmpty()) {
                tick5.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title6","").isEmpty()) {
                tick6.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title7","").isEmpty()) {
                tick7.setAlpha((float) 1.0);
            }


            title=sp.getString("Title1","");
            des=sp.getString("Des1","");

            title1.setText(title);
            des1.setText(des);

            title=sp.getString("Title2","");
            des=sp.getString("Des2","");

            title2.setText(title);
            des2.setText(des);

            title=sp.getString("Title3","");
            des=sp.getString("Des3","");

            title3.setText(title);
            des3.setText(des);

            title=sp.getString("Title4","");
            des=sp.getString("Des4","");

            title4.setText(title);
            des4.setText(des);

            title=sp.getString("Title5","");
            des=sp.getString("Des5","");

            title5.setText(title);
            des5.setText(des);

            title=sp.getString("Title6","");
            des=sp.getString("Des6","");

            title6.setText(title);
            des6.setText(des);

            title=sp.getString("Title7","");
            des=sp.getString("Des7","");

            title7.setText(title);
            des7.setText(des);

        }
        else if(count==8)
        {
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.VISIBLE);
            layout3.setVisibility(View.VISIBLE);
            layout4.setVisibility(View.VISIBLE);
            layout5.setVisibility(View.VISIBLE);
            layout6.setVisibility(View.VISIBLE);
            layout7.setVisibility(View.VISIBLE);
            layout8.setVisibility(View.VISIBLE);

            if(!sp.getString("Title1","").isEmpty()) {
                tick1.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title2","").isEmpty()) {
                tick2.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title3","").isEmpty()) {
                tick3.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title4","").isEmpty()) {
                tick4.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title5","").isEmpty()) {
                tick5.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title6","").isEmpty()) {
                tick6.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title7","").isEmpty()) {
                tick7.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title8","").isEmpty()) {
                tick8.setAlpha((float) 1.0);
            }


            title=sp.getString("Title1","");
            des=sp.getString("Des1","");

            title1.setText(title);
            des1.setText(des);

            title=sp.getString("Title2","");
            des=sp.getString("Des2","");

            title2.setText(title);
            des2.setText(des);

            title=sp.getString("Title3","");
            des=sp.getString("Des3","");

            title3.setText(title);
            des3.setText(des);

            title=sp.getString("Title4","");
            des=sp.getString("Des4","");

            title4.setText(title);
            des4.setText(des);

            title=sp.getString("Title5","");
            des=sp.getString("Des5","");

            title5.setText(title);
            des5.setText(des);

            title=sp.getString("Title6","");
            des=sp.getString("Des6","");

            title6.setText(title);
            des6.setText(des);

            title=sp.getString("Title7","");
            des=sp.getString("Des7","");

            title7.setText(title);
            des7.setText(des);

            title=sp.getString("Title8","");
            des=sp.getString("Des8","");

            title8.setText(title);
            des8.setText(des);


        }
        else if(count==9)
        {
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.VISIBLE);
            layout3.setVisibility(View.VISIBLE);
            layout4.setVisibility(View.VISIBLE);
            layout5.setVisibility(View.VISIBLE);
            layout6.setVisibility(View.VISIBLE);
            layout7.setVisibility(View.VISIBLE);
            layout8.setVisibility(View.VISIBLE);
            layout9.setVisibility(View.VISIBLE);

            if(!sp.getString("Title1","").isEmpty()) {
                tick1.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title2","").isEmpty()) {
                tick2.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title3","").isEmpty()) {
                tick3.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title4","").isEmpty()) {
                tick4.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title5","").isEmpty()) {
                tick5.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title6","").isEmpty()) {
                tick6.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title7","").isEmpty()) {
                tick7.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title8","").isEmpty()) {
                tick8.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title9","").isEmpty()) {
                tick9.setAlpha((float) 1.0);
            }


            title=sp.getString("Title1","");
            des=sp.getString("Des1","");

            title1.setText(title);
            des1.setText(des);

            title=sp.getString("Title2","");
            des=sp.getString("Des2","");

            title2.setText(title);
            des2.setText(des);

            title=sp.getString("Title3","");
            des=sp.getString("Des3","");

            title3.setText(title);
            des3.setText(des);

            title=sp.getString("Title4","");
            des=sp.getString("Des4","");

            title4.setText(title);
            des4.setText(des);

            title=sp.getString("Title5","");
            des=sp.getString("Des5","");

            title5.setText(title);
            des5.setText(des);

            title=sp.getString("Title6","");
            des=sp.getString("Des6","");

            title6.setText(title);
            des6.setText(des);

            title=sp.getString("Title7","");
            des=sp.getString("Des7","");

            title7.setText(title);
            des7.setText(des);

            title=sp.getString("Title8","");
            des=sp.getString("Des8","");

            title8.setText(title);
            des8.setText(des);

            title=sp.getString("Title9","");
            des=sp.getString("Des9","");

            title9.setText(title);
            des9.setText(des);


        }
        else if(count==10)
        {
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.VISIBLE);
            layout3.setVisibility(View.VISIBLE);
            layout4.setVisibility(View.VISIBLE);
            layout5.setVisibility(View.VISIBLE);
            layout6.setVisibility(View.VISIBLE);
            layout7.setVisibility(View.VISIBLE);
            layout8.setVisibility(View.VISIBLE);
            layout9.setVisibility(View.VISIBLE);
            layout10.setVisibility(View.VISIBLE);

            if(!sp.getString("Title1","").isEmpty()) {
                tick1.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title2","").isEmpty()) {
                tick2.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title3","").isEmpty()) {
                tick3.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title4","").isEmpty()) {
                tick4.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title5","").isEmpty()) {
                tick5.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title6","").isEmpty()) {
                tick6.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title7","").isEmpty()) {
                tick7.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title8","").isEmpty()) {
                tick8.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title9","").isEmpty()) {
                tick9.setAlpha((float) 1.0);
            }
            if(!sp.getString("Title10","").isEmpty()) {
                tick10.setAlpha((float) 1.0);
            }


            title=sp.getString("Title1","");
            des=sp.getString("Des1","");

            title1.setText(title);
            des1.setText(des);

            title=sp.getString("Title2","");
            des=sp.getString("Des2","");

            title2.setText(title);
            des2.setText(des);

            title=sp.getString("Title3","");
            des=sp.getString("Des3","");

            title3.setText(title);
            des3.setText(des);

            title=sp.getString("Title4","");
            des=sp.getString("Des4","");

            title4.setText(title);
            des4.setText(des);

            title=sp.getString("Title5","");
            des=sp.getString("Des5","");

            title5.setText(title);
            des5.setText(des);

            title=sp.getString("Title6","");
            des=sp.getString("Des6","");

            title6.setText(title);
            des6.setText(des);

            title=sp.getString("Title7","");
            des=sp.getString("Des7","");

            title7.setText(title);
            des7.setText(des);

            title=sp.getString("Title8","");
            des=sp.getString("Des8","");

            title8.setText(title);
            des8.setText(des);

            title=sp.getString("Title9","");
            des=sp.getString("Des9","");

            title9.setText(title);
            des9.setText(des);

            title=sp.getString("Title10","");
            des=sp.getString("Des10","");

            title10.setText(title);
            des10.setText(des);

        }


        else
        {
            //
        }

        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(count==0)
                {
                    layout1.setVisibility(View.VISIBLE);

                    count=1;
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putInt("Count",count);
                    editor.commit();

                }
                else if(count==1)
                {
                    layout2.setVisibility(View.VISIBLE);

                    count=2;
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putInt("Count",count);
                    editor.commit();
                }
                else if(count==2)
                {
                    layout3.setVisibility(View.VISIBLE);

                    count=3;
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putInt("Count",count);
                    editor.commit();
                }
                else if(count==3)
                {
                    layout4.setVisibility(View.VISIBLE);

                    count=4;
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putInt("Count",count);
                    editor.commit();
                }
                else if(count==4)
                {
                    layout5.setVisibility(View.VISIBLE);

                    count=5;
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putInt("Count",count);
                    editor.commit();

                }
                else if(count==5)
                {
                    layout6.setVisibility(View.VISIBLE);

                    count=6;
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putInt("Count",count);
                    editor.commit();

                }
                else if(count==6)
                {
                    layout7.setVisibility(View.VISIBLE);

                    count=7;
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putInt("Count",count);
                    editor.commit();

                }
                else if(count==7)
                {
                    layout8.setVisibility(View.VISIBLE);

                    count=8;
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putInt("Count",count);
                    editor.commit();

                }
                else if(count==8)
                {
                    layout9.setVisibility(View.VISIBLE);

                    count=9;
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putInt("Count",count);
                    editor.commit();

                }
                else if(count==9)
                {
                    layout10.setVisibility(View.VISIBLE);

                    count=10;
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putInt("Count",count);
                    editor.commit();

                }
                else
                {
                    AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                    builder.setTitle("Notes Limit Reached");
                    builder.setMessage("You cannot create more than "+count+" notes!");

                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    });

                    builder.show();
                }
            }
        });


        tick1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title=title1.getText().toString();
                des=des1.getText().toString();

                if(!title.isEmpty())
                {
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("Title1",title);
                    editor.putString("Des1",des);
                    editor.commit();

                    Toast.makeText(getActivity(),"Successfully saved!",Toast.LENGTH_SHORT).show();

                    tick1.startAnimation(tick_anim);
                }

            }
        });


        tick2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title=title2.getText().toString();
                des=des2.getText().toString();

                if(!title.isEmpty())
                {
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("Title2",title);
                    editor.putString("Des2",des);
                    editor.commit();

                    Toast.makeText(getActivity(),"Successfully saved!",Toast.LENGTH_SHORT).show();

                    tick2.startAnimation(tick_anim);
                }

            }
        });


        tick3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title=title3.getText().toString();
                des=des3.getText().toString();

                if(!title.isEmpty())
                {
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("Title3",title);
                    editor.putString("Des3",des);
                    editor.commit();

                    Toast.makeText(getActivity(),"Successfully saved!",Toast.LENGTH_SHORT).show();

                    tick3.startAnimation(tick_anim);
                }

            }
        });


        tick4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title=title4.getText().toString();
                des=des4.getText().toString();

                if(!title.isEmpty())
                {
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("Title4",title);
                    editor.putString("Des4",des);
                    editor.commit();

                    Toast.makeText(getActivity(),"Successfully saved!",Toast.LENGTH_SHORT).show();

                    tick4.startAnimation(tick_anim);
                }

            }
        });


        tick5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title=title5.getText().toString();
                des=des5.getText().toString();

                if(!title.isEmpty())
                {
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("Title5",title);
                    editor.putString("Des5",des);
                    editor.commit();

                    Toast.makeText(getActivity(),"Successfully saved!",Toast.LENGTH_SHORT).show();

                    tick5.startAnimation(tick_anim);
                }

            }
        });

        tick6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title=title6.getText().toString();
                des=des6.getText().toString();

                if(!title.isEmpty())
                {
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("Title6",title);
                    editor.putString("Des6",des);
                    editor.commit();

                    Toast.makeText(getActivity(),"Successfully saved!",Toast.LENGTH_SHORT).show();

                    tick6.startAnimation(tick_anim);
                }

            }
        });

        tick7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title=title7.getText().toString();
                des=des7.getText().toString();

                if(!title.isEmpty())
                {
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("Title7",title);
                    editor.putString("Des7",des);
                    editor.commit();

                    Toast.makeText(getActivity(),"Successfully saved!",Toast.LENGTH_SHORT).show();

                    tick7.startAnimation(tick_anim);
                }

            }
        });

        tick8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title=title8.getText().toString();
                des=des8.getText().toString();

                if(!title.isEmpty())
                {
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("Title8",title);
                    editor.putString("Des8",des);
                    editor.commit();

                    Toast.makeText(getActivity(),"Successfully saved!",Toast.LENGTH_SHORT).show();

                    tick8.startAnimation(tick_anim);
                }

            }
        });

        tick9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title=title9.getText().toString();
                des=des9.getText().toString();

                if(!title.isEmpty())
                {
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("Title9",title);
                    editor.putString("Des9",des);
                    editor.commit();

                    Toast.makeText(getActivity(),"Successfully saved!",Toast.LENGTH_SHORT).show();

                    tick9.startAnimation(tick_anim);
                }

            }
        });

        tick10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title=title10.getText().toString();
                des=des10.getText().toString();

                if(!title.isEmpty())
                {
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("Title10",title);
                    editor.putString("Des10",des);
                    editor.commit();

                    Toast.makeText(getActivity(),"Successfully saved!",Toast.LENGTH_SHORT).show();

                    tick10.startAnimation(tick_anim);
                }



            }
        });



        title1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if(title1.getText().toString().isEmpty()) {
                    tick1.setAlpha((float) 0.5);
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(title1.getText().toString().isEmpty()) {
                    tick1.setAlpha((float) 0.5);
                }
                else {
                    tick1.setAlpha((float) 1.0);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(title1.getText().toString().isEmpty()) {
                    tick1.setAlpha((float) 0.5);
                }
                else {
                    tick1.setAlpha((float) 1.0);
                }
            }
        });


        title2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if(title2.getText().toString().isEmpty()) {
                    tick2.setAlpha((float) 0.5);
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(title2.getText().toString().isEmpty()) {
                    tick2.setAlpha((float) 0.5);
                }
                else {
                    tick2.setAlpha((float) 1.0);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(title2.getText().toString().isEmpty()) {
                    tick2.setAlpha((float) 0.5);
                }
                else {
                    tick2.setAlpha((float) 1.0);
                }
            }
        });


        title3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if(title3.getText().toString().isEmpty()) {
                    tick3.setAlpha((float) 0.5);
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(title3.getText().toString().isEmpty()) {
                    tick3.setAlpha((float) 0.5);
                }
                else {
                    tick3.setAlpha((float) 1.0);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(title3.getText().toString().isEmpty()) {
                    tick3.setAlpha((float) 0.5);
                }
                else {
                    tick3.setAlpha((float) 1.0);
                }
            }
        });


        title4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if(title4.getText().toString().isEmpty()) {
                    tick4.setAlpha((float) 0.5);
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(title4.getText().toString().isEmpty()) {
                    tick4.setAlpha((float) 0.5);
                }
                else {
                    tick4.setAlpha((float) 1.0);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(title4.getText().toString().isEmpty()) {
                    tick4.setAlpha((float) 0.5);
                }
                else {
                    tick4.setAlpha((float) 1.0);
                }
            }
        });



        title5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if(title5.getText().toString().isEmpty()) {
                    tick5.setAlpha((float) 0.5);
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(title5.getText().toString().isEmpty()) {
                    tick5.setAlpha((float) 0.5);
                }
                else {
                    tick5.setAlpha((float) 1.0);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(title5.getText().toString().isEmpty()) {
                    tick5.setAlpha((float) 0.5);
                }
                else {
                    tick5.setAlpha((float) 1.0);
                }
            }
        });



        title6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if(title6.getText().toString().isEmpty()) {
                    tick6.setAlpha((float) 0.5);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(title6.getText().toString().isEmpty()) {
                    tick6.setAlpha((float) 0.5);
                }
                else {
                    tick6.setAlpha((float) 1.0);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(title6.getText().toString().isEmpty()) {
                    tick6.setAlpha((float) 0.5);
                }
                else {
                    tick6.setAlpha((float) 1.0);
                }
            }
        });



        title7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if(title7.getText().toString().isEmpty()) {
                    tick7.setAlpha((float) 0.5);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(title7.getText().toString().isEmpty()) {
                    tick7.setAlpha((float) 0.5);
                }
                else {
                    tick7.setAlpha((float) 1.0);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(title7.getText().toString().isEmpty()) {
                    tick7.setAlpha((float) 0.5);
                }
                else {
                    tick7.setAlpha((float) 1.0);
                }
            }
        });



        title8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if(title8.getText().toString().isEmpty()) {
                    tick8.setAlpha((float) 0.5);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(title8.getText().toString().isEmpty()) {
                    tick8.setAlpha((float) 0.5);
                }
                else {
                    tick8.setAlpha((float) 1.0);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(title8.getText().toString().isEmpty()) {
                    tick8.setAlpha((float) 0.5);
                }
                else {
                    tick8.setAlpha((float) 1.0);
                }
            }
        });



        title9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if(title9.getText().toString().isEmpty()) {
                    tick9.setAlpha((float) 0.5);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(title9.getText().toString().isEmpty()) {
                    tick9.setAlpha((float) 0.5);
                }
                else {
                    tick9.setAlpha((float) 1.0);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(title9.getText().toString().isEmpty()) {
                    tick9.setAlpha((float) 0.5);
                }
                else {
                    tick9.setAlpha((float) 1.0);
                }
            }
        });



        title10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if(title10.getText().toString().isEmpty()) {
                    tick10.setAlpha((float) 0.5);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(title10.getText().toString().isEmpty()) {
                    tick10.setAlpha((float) 0.5);
                }
                else {
                    tick10.setAlpha((float) 1.0);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(title10.getText().toString().isEmpty()) {
                    tick10.setAlpha((float) 0.5);
                }
                else {
                    tick10.setAlpha((float) 1.0);
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}