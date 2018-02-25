package com.adgvit.appathon2k18.appathon2k18;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class Quiz extends AppCompatActivity {


    Button optiona;
    Button optionb;
    Button optionc;
    Button optiond;
    TextView q,timer;
    Map<String,String> quiz,quiz_option,quiz_ans,quiz1,quiz1_option,quiz1_ans,quiz2,quiz2_option,quiz2_ans,quiz3,quiz3_option,quiz3_ans,quiz4,quiz4_option,quiz4_ans;
    String[] arr;
    int count=1,score;
    Button last_button;
    String sel_ans;
    ImageButton next;
    SharedPreferences sp;
    String Reg_Num;DatabaseReference rf;
    FirebaseDatabase db;
    CountDownTimer countDownTimer;
    float a= (float) 0.9;
    float b=(float) 0.5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        optiona = findViewById(R.id.button2);
        optionb = findViewById(R.id.button3);
        optionc = findViewById(R.id.button4);
        optiond = findViewById(R.id.button5);
        q = findViewById(R.id.tv_q);
        timer = findViewById(R.id.timer1);
        next = findViewById(R.id.button6);


        final Intent intent1=new Intent(getApplicationContext(),HomeScreen.class);


        sp = Quiz.this.getSharedPreferences("key", 0);
        Reg_Num = sp.getString("Reg_Num", "");
        Intent intent=getIntent();
        int round=intent.getIntExtra("round",0);

        db=FirebaseDatabase.getInstance();
        rf=db.getReference().child(Reg_Num);
        rf.child("attempted").setValue(round);


        //declaration of hashmap
        quiz = new HashMap<>();
        quiz_option = new HashMap<>();
        quiz_ans = new HashMap<>();

        quiz1 = new HashMap<>();
        quiz1_option = new HashMap<>();
        quiz1_ans = new HashMap<>();

        quiz2 = new HashMap<>();
        quiz2_option = new HashMap<>();
        quiz2_ans = new HashMap<>();

        quiz3 = new HashMap<>();
        quiz3_option = new HashMap<>();
        quiz3_ans = new HashMap<>();

        quiz4 = new HashMap<>();
        quiz4_option = new HashMap<>();
        quiz4_ans = new HashMap<>();


        //question declaration
        quiz1.put("1", "what is a hack a thon?");
        quiz1.put("2", "What is ADG?");
        quiz1.put("3", "what language for android app dev");

        quiz1_option.put("1", "a,b,c,d");
        quiz1_option.put("2", "apple developers group,adobe developers group,ajax developers group,no idea");

        quiz1_ans.put("1", "a");
        quiz1_ans.put("2", "a");
        switch (round)
        {
            case 1:
                quiz=quiz1;
                quiz_ans=quiz1_ans;
                quiz_option=quiz1_option;
                break;
            case 2:
                quiz=quiz2;
                quiz_ans=quiz2_ans;
                quiz_option=quiz2_option;
                break;
            case 3:
                quiz=quiz3;
                quiz_ans=quiz3_ans;
                quiz_option=quiz3_option;
                break;
            case 4:
                quiz=quiz4;
                quiz_ans=quiz4_ans;
                quiz_option=quiz4_option;
                break;
        }

        q.setText(quiz.get("1"));
        arr = quiz_option.get("1").split(",");
        for (int i = 0; i < 4; i++) {
            optiona.setText(arr[0]);
            optionb.setText(arr[1]);
            optionc.setText(arr[2]);
            optiond.setText(arr[3]);
        }
        countDownTimer=new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long l)
            {
//                timer_minute.setText(""+l/60000);
                timer.setText("" + String.format("%d : %d", TimeUnit.MILLISECONDS.toMinutes(l), TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l))));
                if (TimeUnit.MILLISECONDS.toMinutes(l) == 0 && TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)) < 10) {
                    timer.setTextColor(getResources().getColor(R.color.timer));
                }

            }
            @Override
            public void onFinish()
            {
                Toast.makeText(getApplicationContext(), "Your time's up!", Toast.LENGTH_LONG).show();
                rf.child("marks").setValue(score);
                finish();

            }
        };
        countDownTimer.start();


    }

    @Override
    protected void onStart() {

        last_button = optiona;
        optiona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optiona.setAlpha(a);
                optiona.setTextColor(getResources().getColor(R.color.timer));
                if (last_button != optiona) {
                    last_button.setAlpha(b);
                    last_button.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                }
                last_button = optiona;
                sel_ans = "a";
            }
        });

        optionb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                optionb.setAlpha(a);
                optionb.setTextColor(getResources().getColor(R.color.timer));
                if (last_button != optionb) {
                    last_button.setAlpha(b);
                    last_button.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                }
                last_button = optionb;
                sel_ans = "b";
            }
        });

        optionc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionc.setAlpha(a);
                optionc.setTextColor(getResources().getColor(R.color.timer));
                if (last_button != optionc)
                {
                    last_button.setAlpha(b);
                    last_button.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                }
                last_button = optionc;
                sel_ans = "c";
            }
        });

        optiond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optiond.setAlpha(a);
                optiond.setTextColor(getResources().getColor(R.color.timer));
                if (last_button != optiond) {
                    last_button.setAlpha(b);
                    last_button.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                }
                last_button = optiond;
                sel_ans = "d";
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = quiz_ans.get("" + count);
                if (answer.equalsIgnoreCase(sel_ans))
                    score++;
                count++;

                if (count < 3)
                {
                    last_button.setAlpha(b);
                    last_button.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    q.setText(quiz.get("" + count));
                    arr = quiz_option.get("" + count).split(",");
                    for (int i = 0; i < 4; i++)
                    {
                        optiona.setText(arr[0]);
                        optionb.setText(arr[1]);
                        optionc.setText(arr[2]);
                        optiond.setText(arr[3]);
                    }
                }
                else
                {
                    rf.child("marks").setValue(score);
                    finish();
                    //Establish firebase connection and push the score to the database.
                    //End the current activity and take the user out of the quiz.
                    //take the user back to the menu.
                }
            }
        });

        super.onStart();
    }

    @Override
    protected void onStop()
    {
        countDownTimer.cancel();
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference rf=db.getReference().child(Reg_Num);
        rf.child("marks").setValue(score);
        Toast.makeText(getBaseContext(),"Wrong move! Quiz ended. Score submitted",Toast.LENGTH_SHORT).show();
        finish();
        super.onStop();
    }

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder;
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP)
            builder=new AlertDialog.Builder(Quiz.this,android.R.style.Theme_DeviceDefault_Dialog);

        else
            builder=new AlertDialog.Builder(Quiz.this);

        builder.setMessage("Are you sure you want to end your quiz?").setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                FirebaseDatabase db=FirebaseDatabase.getInstance();
                DatabaseReference rf=db.getReference().child(Reg_Num);
                rf.child("marks").setValue(score);
                finish();
            }
        }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).setIcon(android.R.drawable.ic_dialog_alert).show();

    }


}