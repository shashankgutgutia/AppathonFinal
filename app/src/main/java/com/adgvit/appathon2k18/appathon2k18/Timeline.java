package com.adgvit.appathon2k18.appathon2k18;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Timeline extends AppCompatActivity {

    RecyclerView recyclerView;
    TimelineAdapter tAdapter;
    RecyclerView.LayoutManager layoutManager;
    private ArrayList<TimelineItems> timelineItems = new ArrayList<>();
    private String[] times={"5:15 pm","5:30 pm","6:00 pm","6:15 pm","6:30 pm","7:15 pm","8:00 pm","10:30 pm","2:00 am",
            "3:15 pm","3:30 pm","4:00 pm","4:15 pm","4:45 pm","7:30 pm","8:30 pm","11:30 pm"};

    private String[] title={"Welcome to UniDev","Reporting Time","Commencement","Inauguration","Speakers take over","Break","Workshop is Resumed","Short Break","Wrap Up",
            "Registration Desk Setup","Reporting Time","Workshop Resumes","Break","Workshop Resumes","Break","Workshop Resumes","Workshop is Concluded"};

    private String[] details={"Registration Desks Setup","Reporting Time for Participants","Commencement of the Workshop","Inaugural address detailing chapter","Step by step walk-through","The time arrives for relaxation","Time to get back to Work","A short break for 30 mins!","Pack your bags and head home",
            "Registration Desks Setup","Reporting Time for Participants","The workshop resumes","Time for you to relax","Get back to work","Take a break","The final session","Hope to see you next year!"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        try {
            setContentView(R.layout.activity_timeline);
            recyclerView = findViewById(R.id.recyclerid);
            int count = 0;
            for (String Name : title) {
                timelineItems.add(new TimelineItems(times[count], Name, details[count]));
                count++;
            }
            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            tAdapter = new TimelineAdapter(timelineItems, this);
            recyclerView.setAdapter(tAdapter);
            Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move2);
            recyclerView.startAnimation(anim);

            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+5:30"));
            Date currentLocalTime = cal.getTime();
            DateFormat date = new SimpleDateFormat("HH");
            // you can get seconds by adding  "...:ss" to it
            date.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
            String localTime = date.format(currentLocalTime);
            Log.w(localTime, "1");
            RelativeLayout backgroundtimeline = findViewById(R.id.homeback);

            if (localTime.equals("06") || localTime.equals("07") || localTime.equals("08") || localTime.equals("09") || localTime.equals("10") || localTime.equals("11") || localTime.equals("12")) {
                backgroundtimeline.setBackgroundResource(R.drawable.back1);

            }
            if (localTime.equals("13") || localTime.equals("14") || localTime.equals("15") || localTime.equals("16")) {
                backgroundtimeline.setBackgroundResource(R.drawable.back2);

            }
            if (localTime.equals("17") || localTime.equals("18") || localTime.equals("19") || localTime.equals("20") || localTime.equals("21") || localTime.equals("22") || localTime.equals("23") || localTime.equals("24") || localTime.equals("00") || localTime.equals("01") || localTime.equals("02") || localTime.equals("03") || localTime.equals("04") || localTime.equals("05")) {
                backgroundtimeline.setBackgroundResource(R.drawable.back3);

            }
        }
        catch (Exception e){
            Toast.makeText(Timeline.this, "Connection Issue.Try Again", Toast.LENGTH_SHORT).show();
        }
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
        try {
            Intent intent = new Intent(Timeline.this, HomeScreen.class);
            startActivity(intent);
        }
        catch (Exception e){
            Toast.makeText(Timeline.this, "Connection Issue.Try Again", Toast.LENGTH_SHORT).show();
        }
    }
}
