package com.adgvit.appathon2k18.appathon2k18;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Sponsors extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String[] f_name;
    RelativeLayout lr;

    int[] Img_res={R.drawable.shiram,R.drawable.jetbrains,R.drawable.souled_store,R.drawable.coveritup,R.drawable.venson,R.drawable.marposs,R.drawable.brandstik,R.drawable.tech_logo,R.drawable.wolfram,R.drawable.zeplin};

    ArrayList<Dataprovider>arrayList=new ArrayList<Dataprovider>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_sponsors);


        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+5:30"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH");
        // you can get seconds by adding  "...:ss" to it
        date.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
        String localTime = date.format(currentLocalTime);
        Log.w(localTime, "1");

        lr=(RelativeLayout) findViewById(R.id.lr);
        if (localTime.equals("06")||localTime.equals("07")||localTime.equals("08")||localTime.equals("09")||localTime.equals("10")||localTime.equals("11")||localTime.equals("12"))
        {
            lr.setBackgroundResource(R.drawable.back1);

        }
        if (localTime.equals("13")||localTime.equals("14")||localTime.equals("15")||localTime.equals("16"))
        {
            lr.setBackgroundResource(R.drawable.back2);

        }
        if(localTime.equals("17")||localTime.equals("18")||localTime.equals("19")||localTime.equals("20")||localTime.equals("21")||localTime.equals("22")||localTime.equals("23")||localTime.equals("24")||localTime.equals("00")||localTime.equals("01")||localTime.equals("02")||localTime.equals("03")||localTime.equals("04")||localTime.equals("05"))
        {
            lr.setBackgroundResource(R.drawable.back3);

        }


        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        f_name=getResources().getStringArray(R.array.sponsornames);
        int i=0;
        for(String name:f_name)
        {
            Dataprovider dataprovider=new Dataprovider(Img_res[i],name);
            arrayList.add(dataprovider);
            i++;
        }
        adapter=new RecyclerAdapter(arrayList);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==android.R.id.home) {
            finish();
        }
        return true;
    }

    public void backHome(View v){
        Intent intent=new Intent(Sponsors.this, Settings.class);
        startActivity(intent);
    }
}
