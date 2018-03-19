package com.adgvit.appathon2k18.appathon2k18;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.HeterogeneousExpandableList;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Shashank Gutgutia on 05-02-2018.
 */

public class ExpandableAdapter extends BaseExpandableListAdapter implements HeterogeneousExpandableList{

    private Context context;
    private List<String> listDataHeader; // header titles
    SharedPreferences sp;
    TimelinePageAdapter timelinePageAdapter;
    ViewPager mViewPager;
    // Child data in format of header title, child title
    //private HashMap<String, List<String>> listDataChild;

    private String[] times={"4:00-5:00 pm","5:00-6:00 pm","6:00-7:00 pm","7:00-8:00 pm","8:00-9:00 pm","9:00-10:00 pm","10:00-11:00 pm","11:00-12:00 am","2:00 pm",
            "3:15 pm","3:30 pm","4:00 pm","4:15 pm","4:45 pm","7:30 pm","8:30 pm","11:30 pm"};

    private String[] title={"Welcome to UniDev","Reporting Time","Commencement","Inauguration","Speakers take over","Break","Workshop is Resumed","Short Break","Wrap Up",
            "Registration Desk Setup","Reporting Time","Workshop Resumes","Break","Workshop Resumes","Break","Workshop Resumes","Workshop is Concluded"};

    private String[] details={"Registration Desks Setup","Reporting Time for Participants","Commencement of the Workshop","Inaugural address detailing chapter","Step by step walk-through","The time arrives for relaxation","Time to get back to Work","A short break for 30 mins!","Pack your bags and head home",
            "Registration Desks Setup","Reporting Time for Participants","The workshop resumes","Time for you to relax","Get back to work","Take a break","The final session","Hope to see you next year!"};

    public ExpandableAdapter(Context context, List<String> listDataHeader) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        //this.listDataChild = listChildData;
    }

    @Override
    public int getGroupCount() {
        return this.listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        //return this.listDataChild.get(this.listDataHeader.get(groupPosition)).size();
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return "";
        //return this.listDataChild.get(this.listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        try {
            String headerTitle = (String) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) this.context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_group, null);
            }

            TextView lblListHeader = (TextView) convertView.findViewById(R.id.listHeader);
            ImageView imageView = convertView.findViewById(R.id.arrow);
            ImageView imageView2 = convertView.findViewById(R.id.grpIcon);

            //Typeface mycustomfont=Typeface.createFromAsset(getAssets(),"font/SF-Compact-Display-Thin.otf");
            //lblListHeader.setTypeface(mycustomfont);


            lblListHeader.setText(headerTitle);
            if (groupPosition == 0) {
                imageView2.setImageResource(R.drawable.timeline);
            } else if (groupPosition == 1) {
                imageView2.setImageResource(R.drawable.wifi);
            } else if (groupPosition == 2) {
                imageView2.setImageResource(R.drawable.food);
            } else if (groupPosition == 3) {
                imageView2.setImageResource(R.drawable.quiz);
            } else {
                imageView2.setImageResource(R.drawable.gift);
            }

            if (isExpanded) {
                imageView.setImageResource(R.drawable.up);
            } else {
                imageView.setImageResource(R.drawable.down);
            }
        }
        catch (Exception e){
            Toast.makeText(parent.getContext(), "Connection Issue.Try Again", Toast.LENGTH_SHORT).show();
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        try {
            final String childText = (String) getChild(groupPosition, childPosition);
            LayoutInflater layoutInflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            sp = context.getSharedPreferences("key", 0);
            if (groupPosition == 0) {

                convertView = layoutInflater.inflate(R.layout.list_timeline, null);
                TextView descrpt = convertView.findViewById(R.id.title);
                TextView location = convertView.findViewById(R.id.location);
                TextView time = convertView.findViewById(R.id.time);

                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+5:30"));
                Date currentLocalTime = cal.getTime();
                DateFormat date = new SimpleDateFormat("HH");
                // you can get seconds by adding  "...:ss" to it
                date.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
                String localTime = date.format(currentLocalTime);
                String date1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                String date2 = date1.substring(date1.length() - 2);
                if (date2.equals("14")) {
                    if (Integer.parseInt(localTime) > 15) {
                        int ind = Integer.parseInt(localTime) - 16;
                        descrpt.setText(title[ind]);
                        location.setText(details[ind]);
                        time.setText(times[ind]);
                    }
                } else if (date2.equals("15")) {
                    if (Integer.parseInt(localTime) > 13 && Integer.parseInt(localTime) <= 23) {
                        int ind = Integer.parseInt(localTime) - 16;
                        descrpt.setText(title[ind]);
                        location.setText(details[ind]);
                        time.setText(times[ind]);
                    } else if (Integer.parseInt(date2) > 15) {
                        descrpt.setText("Workshop has concluded");
                        location.setText("Hope to see you next year");
                        time.setText("");
                    }
                }
                /*
                timelinePageAdapter=new TimelinePageAdapter(((AppCompatActivity)context).getSupportFragmentManager());
                convertView = layoutInflater.inflate(R.layout.list_timeline, null);
                mViewPager= (ViewPager) convertView.findViewById(R.id.pager);
                mViewPager.setAdapter(timelinePageAdapter);
                int cr=mViewPager.getCurrentItem();

                Log.d("Timeline", Integer.toString(cr));
                int current = sp.getInt("Position",-2);
                if(current==-2){
                    SharedPreferences.Editor sedt1 = sp.edit();
                    sedt1.putInt("Position", 0);
                    sedt1.apply();
                }
                else if(current==1){
                    int cr=mViewPager.getCurrentItem();
                    Log.d("Timeline", (String.valueOf(cr)));
                    if(cr<4)
                        mViewPager.setCurrentItem(1);
                }
                else if(current==-1){
                    int cr=mViewPager.getCurrentItem();
                    Log.d("Timeline", (String.valueOf(cr)));
                    if(cr>0)
                        mViewPager.setCurrentItem(4);
                }
                */
            } else if (groupPosition == 1) {
                convertView = layoutInflater.inflate(R.layout.list_wifi, null);
                String wifiname = sp.getString("wUser", "");
                String wifipass = sp.getString("wPass", "");
                if (!wifiname.equalsIgnoreCase("") || !wifipass.equalsIgnoreCase("")) {
                    TextView username = convertView.findViewById(R.id.userName);
                    TextView password = convertView.findViewById(R.id.passWord);
                    username.setText(wifiname);
                    password.setText(wifipass);
                    username.setVisibility(View.VISIBLE);
                    password.setVisibility(View.VISIBLE);
                }

            } else if (groupPosition == 2) {
                convertView = layoutInflater.inflate(R.layout.list_food, null);
                ImageView imageView = (ImageView) convertView.findViewById(R.id.barcode);
                String prevEncodedImage = sp.getString("food", "");
                Boolean fstatus = sp.getBoolean("fcheck", false);
                if (!prevEncodedImage.equalsIgnoreCase("")) {
                    byte[] b = Base64.decode(prevEncodedImage, Base64.DEFAULT);
                    Log.d("test",b.toString());
                    Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
                    imageView.setImageBitmap(bitmap);
                }
                if (fstatus) {
                    TextView status = convertView.findViewById(R.id.redeem);
                    status.setText(R.string.food_check);
                } else {
                    TextView status = convertView.findViewById(R.id.redeem);
                    status.setText(R.string.nfood_check);
                }


            } else if (groupPosition == 4) {
                convertView = layoutInflater.inflate(R.layout.list_goodies, null);
                ImageView imageView1 = (ImageView) convertView.findViewById(R.id.barcodeGoodies);
                String prevEncodedImage1 = sp.getString("goodies", "");
                Log.d("fgh",prevEncodedImage1);
                Boolean gstatus = sp.getBoolean("gcheck", false);
                if (!prevEncodedImage1.equalsIgnoreCase("")) {
                    byte[] b1 = Base64.decode(prevEncodedImage1, Base64.DEFAULT);
                    Log.d("test",b1.toString());
                    Bitmap bitmap1 = BitmapFactory.decodeByteArray(b1, 0, b1.length);
                    imageView1.setImageBitmap(bitmap1);
                }
                if (gstatus) {
                    TextView status = convertView.findViewById(R.id.receive);
                    status.setText(R.string.good_check);
                } else {
                    TextView status = convertView.findViewById(R.id.receive);
                    status.setText(R.string.ngood_check);
                }

            } else {
                convertView = layoutInflater.inflate(R.layout.list_quiz, null);
            }
        }
        catch (Exception e){
            Toast.makeText(parent.getContext(), "Connection Issue.Try Again", Toast.LENGTH_SHORT).show();
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
