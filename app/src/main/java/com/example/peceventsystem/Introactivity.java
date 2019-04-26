package com.example.peceventsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Introactivity extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    Button btnNext;
    int position  = 0;
    Button btnGetStarted;
    Animation btnanim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        // when restore

        if(restorePrefData())
        {
            Intent mainActivity = new Intent(getApplicationContext(), loginactivity.class);
            startActivity(mainActivity);
            finish();
        }

        setContentView(R.layout.activity_introactivity);

        // hide action bar

        getSupportActionBar().hide();

        //views
        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_get_started);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnanim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);

        //list

        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Welcome \nto \nPEC Event System","We made it easier for you to register for PEC's events!",R.drawable.logo));
        mList.add(new ScreenItem("Event","Select your choice of events...",R.drawable.reg));
        mList.add(new ScreenItem("Register","Register for your favourite events and you are set!",R.drawable.reg));

        //setup ViewPager
        screenPager = findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this,mList);
        screenPager.setAdapter(introViewPagerAdapter);

        // setup tablayout with page view

        tabIndicator.setupWithViewPager(screenPager);


        // next button click
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = screenPager.getCurrentItem();
                if (position<mList.size())
                {
                    position++;
                    screenPager.setCurrentItem(position);
                }
                if(position == mList.size()-1)
                {
                    //get started button
                    loadLastScreen();
                }

            }
        });

        // in tablayout
        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(tab.getPosition() == mList.size()-1)
                {
                    loadLastScreen();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // button get started click
         btnGetStarted.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 // open Main Activity
                 Intent mainActivity = new Intent(getApplicationContext(), loginactivity.class);
                 startActivity(mainActivity);

                 // not show intro next time

                 saveprefsData();
                 finish();

             }
         });



    }

    private boolean restorePrefData()
    {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntroActivityOpenedBefore = pref.getBoolean("isIntroOpened", false);
        return isIntroActivityOpenedBefore;

    }

    private void saveprefsData()
    {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpened", true);
        editor.commit();

    }

    private void loadLastScreen()
    {
        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);

        // animation
        btnGetStarted.setAnimation(btnanim);
    }
}
