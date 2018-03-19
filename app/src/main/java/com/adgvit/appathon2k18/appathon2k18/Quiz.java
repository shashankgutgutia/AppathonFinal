package com.adgvit.appathon2k18.appathon2k18;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

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
    TextView q, timer;
    Map<String, String> quiz, quiz_option, quiz_ans, quiz1, quiz1_option, quiz1_ans, quiz2, quiz2_option, quiz2_ans, quiz3, quiz3_option, quiz3_ans, quiz4, quiz4_option, quiz4_ans;
    String[] arr;
    int count = 1, score;
    Button last_button;
    String sel_ans;
    ImageButton next;
    SharedPreferences sp;
    String Reg_Num;
    DatabaseReference rf;
    FirebaseDatabase db;
    CountDownTimer countDownTimer;
    float a = (float) 0.9;
    float b = (float) 0.5;
    int check,i;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quiz);
        optiona = findViewById(R.id.button2);
        optionb = findViewById(R.id.button3);
        optionc = findViewById(R.id.button4);
        optiond = findViewById(R.id.button5);
        q = findViewById(R.id.tv_q);
        timer = findViewById(R.id.timer1);
        next = findViewById(R.id.button6);


        sp = Quiz.this.getSharedPreferences("key", 0);
        Reg_Num = sp.getString("Reg_Num", "");
        Intent intent = getIntent();
        int round = intent.getIntExtra("round", 0);
        score = intent.getIntExtra("score", 0);

        db = FirebaseDatabase.getInstance();
        rf = db.getReference().child(Reg_Num);
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
        quiz1.put("1", "Given : var stringValue:String = “Justin Gif”\n" + "What is the result of stringValue = nil ?");
        quiz1.put("2", "Which of the following is not a valid declaration in C?");
        quiz1.put("3", "Which type of data file is analogous to an audio cassette tape?\n");
        quiz1.put("4", "Write the output of the following;\n" +
                "char *mychar; // points to memory location 1000\n" +
                "short *mychart; // points to memory location 2000\n" +
                "long *mylong// points to memory location 3000\n" +
                "mychar++;\n" +
                "++myshort;\n" +
                "mylong++;\n" +
                "count<<mychar<<myshort<<mylong;");
        quiz1.put("5", "Which one of the following is not a valid reserved keyword in C++");
        quiz1.put("6", "Value of a in a = (b = 5, b + 5) is");
        quiz1.put("7", "Given an empty function of type void, what value is shown when executed at the shell?\n");
        quiz1.put("8", "What is the output of print(math.factorial(4.5))?");
        quiz1.put("9", "Is the following piece of code valid? (In Python) \n" +
                "a={1,4,{6,9}}\n" +
                "print(a[2][0])");
        quiz1.put("10", "a={'B':5,'A':9,'C':7}\n" +
                "print(sorted(a))");

        quiz2.put("1", "x = ['wx', 'ya']\n" +
                "print(list(map(list, x)))\n" +
                "What is the output?");
        quiz2.put("2", "flag = [[5, 2, 7],\n" +
                "      [2, 7, 8],\n" +
                "      [1, 4, 1]]\n" +
                "[flag[i][1] for i in (0, 1, 2)]");
        quiz2.put("3", "print(\"areuserious\".find(\"se\") == \"se\" in \"areuserious\")");
        quiz2.put("4", "A binary search tree whose left subtree and right subtree differ in hight by at most 1 unit is called ……?");
        quiz2.put("5", "…………… is not the component of data structure.");
        quiz2.put("6", "Which of the following data structure can’t store the non-homogeneous data elements?");
        quiz2.put("7", "Apriory algorithm analysis does not include −");
        quiz2.put("8", "Access time of a binary search tree may go worse in terms of time complexity upto");
        quiz2.put("9", "if __name__ == \"__main__\":\n" + "somemethod()");
        quiz2.put("10", "In python 3 what does // operator do ?");


        //options
        quiz1_option.put("1", "stringValue == nil;stringValue == “Justin Gif”;the compiler won't allow it;None of the above");
        quiz1_option.put("2", "short int x;signed short x;short x;unsigned short x;");
        quiz1_option.put("3", "random access file;sequential access file;binary file;source code file");
        quiz1_option.put("4", "1001 2001 3001;1001 2002 3004;1001 2001 3002;1001 2002 3004");
        quiz1_option.put("5", "Explicit;Public;Implicit;Private");
        quiz1_option.put("6", "Junk value;Syntax error;5;10");
        quiz1_option.put("7", "int;bool;void;none");
        quiz1_option.put("8", "24;120;error;20");
        quiz1_option.put("9", "Yes, 6 is printed;Error elements of a set can’t be printed;Error, subsets aren’t allowed;Yes;{6,9} is printed");
        quiz1_option.put("10", "['A','B','C'].;['B','C','A'].;[5,7,9].;[9,5,7].");


        quiz2_option.put("1", "['w','x','y','a'].;[['wx'], ['ya']].;[['w', 'x'] ['y','a']].;Error");
        quiz2_option.put("2", " [5, 2, 7];[7, 8, 1];[2, 7, 4];[1, 4, 1]");
        quiz2_option.put("3", "True;False;Error;None of the mentioned");
        quiz2_option.put("4", "AVL tree;Red-black tree;Lemma tree;None of the above");
        quiz2_option.put("5", "Operations;Storage Structures;Algorithms;Stacks");
        quiz2_option.put("6", "Arrays;Records;Pointers;no idea");
        quiz2_option.put("7", "Time Complexity;Space Complexity;Program Complexity;None of the above!");
        quiz2_option.put("8", "Ο(n2);Ο(n log n);Ο(n);Ο(1)");
        quiz2_option.put("9", "Create new module;Define generators;Run python module as main program;Create new objects");
        quiz2_option.put("10", "Float division;Integer division;returns remainder;same as a**b");


        //answer
        quiz1_ans.put("1", "c");
        quiz1_ans.put("2", "d");
        quiz1_ans.put("3", "b");
        quiz1_ans.put("4", "d");
        quiz1_ans.put("5", "c");
        quiz1_ans.put("6", "d");
        quiz1_ans.put("7", "d");
        quiz1_ans.put("8", "c");
        quiz1_ans.put("9", "c");
        quiz1_ans.put("10", "a");

        quiz2_ans.put("1", "c");
        quiz2_ans.put("2", "c");
        quiz2_ans.put("3", "b");
        quiz2_ans.put("4", "a");
        quiz2_ans.put("5", "d");
        quiz2_ans.put("6", "a");
        quiz2_ans.put("7", "c");
        quiz2_ans.put("8", "c");
        quiz2_ans.put("9", "c");
        quiz2_ans.put("10", "b");

        switch (round) {
            case 1:
                quiz = quiz1;
                quiz_ans = quiz1_ans;
                quiz_option = quiz1_option;
                break;
            case 2:
                quiz = quiz2;
                quiz_ans = quiz2_ans;
                quiz_option = quiz2_option;
                break;
        }

        q.setText(quiz.get("1"));
        arr = quiz_option.get("1").split(";");
        for (int i = 0; i < 4; i++) {
            optiona.setText("A: "+arr[0]);
            optionb.setText("B: "+arr[1]);
            optionc.setText("C: "+arr[2]);
            optiond.setText("D: "+arr[3]);
        }

        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long l)
            {

                if (TimeUnit.MILLISECONDS.toMinutes(l) == 0 && TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)) < 10)
                {
                    timer.setText("" + String.format("%s%d : %s%d", "0",TimeUnit.MILLISECONDS.toMinutes(l),"0",(TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)))));
                }
                else if(TimeUnit.MILLISECONDS.toMinutes(l) == 0 && TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)) >= 10)
                {
                    timer.setText("" + String.format("%s%d : %d","0",TimeUnit.MILLISECONDS.toMinutes(l), TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l))));
                }

            }
            @Override
            public void onFinish()
            {
                if(count<11)
                {
                    countDownTimer.cancel();
                    nextClick(getCurrentFocus());

                }
                else
                {
                    countDownTimer.cancel();
                    check=0;
                    rf.child("marks").setValue(score);
                    Intent returnIntent=new Intent();
                    returnIntent.putExtra("check",check);
                    returnIntent.putExtra("score",score);
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                }
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
                optiona.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                optiona.setTextColor(getResources().getColor(R.color.timer));
                if (last_button != optiona) {
                    if(last_button==optionb)
                    {
                        last_button.setBackground(getResources().getDrawable(R.drawable.button_quiz2));
                    }
                    else if(last_button==optionc)
                    {
                        last_button.setBackground(getResources().getDrawable(R.drawable.button_quiz3));
                    }
                    else if(last_button==optiond)
                    {
                        last_button.setBackground(getResources().getDrawable(R.drawable.button_quiz4));
                    }
                    last_button.setTextColor(getResources().getColor(R.color.colorWhite));
                }
                last_button = optiona;
                sel_ans = "a";
            }
        });

        optionb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionb.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                optionb.setTextColor(getResources().getColor(R.color.timer));
                if (last_button != optionb) {
                    if(last_button==optiona)
                    {
                        last_button.setBackground(getResources().getDrawable(R.drawable.button_quiz1));
                    }
                    else if(last_button==optionc)
                    {
                        last_button.setBackground(getResources().getDrawable(R.drawable.button_quiz3));
                    }
                    else if(last_button==optiond)
                    {
                        last_button.setBackground(getResources().getDrawable(R.drawable.button_quiz4));
                    }

                    last_button.setTextColor(getResources().getColor(R.color.colorWhite));
                }
                last_button = optionb;
                sel_ans = "b";
            }
        });

        optionc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionc.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                optionc.setTextColor(getResources().getColor(R.color.timer));
                if (last_button != optionc) {
                    if(last_button==optiona)
                    {
                        last_button.setBackground(getResources().getDrawable(R.drawable.button_quiz1));
                    }
                    else if(last_button==optionb)
                    {
                        last_button.setBackground(getResources().getDrawable(R.drawable.button_quiz2));
                    }
                    else if(last_button==optiond)
                    {
                        last_button.setBackground(getResources().getDrawable(R.drawable.button_quiz4));
                    }
                    last_button.setTextColor(getResources().getColor(R.color.colorWhite));
                }
                last_button = optionc;
                sel_ans = "c";
            }
        });

        optiond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optiond.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                optiond.setTextColor(getResources().getColor(R.color.timer));
                if (last_button != optiond) {
                    if(last_button==optiona)
                    {
                        last_button.setBackground(getResources().getDrawable(R.drawable.button_quiz1));
                    }
                    else if(last_button==optionb)
                    {
                        last_button.setBackground(getResources().getDrawable(R.drawable.button_quiz2));
                    }
                    else if(last_button==optionc)
                    {
                        last_button.setBackground(getResources().getDrawable(R.drawable.button_quiz3));
                    }

                    last_button.setTextColor(getResources().getColor(R.color.colorWhite));
                }
                last_button = optiond;
                sel_ans = "d";
            }
        });

        super.onStart();
    }




    @Override
    protected void onStop()
    {
        super.onStop();

        countDownTimer.cancel();
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference rf=db.getReference().child(Reg_Num);
        rf.child("marks").setValue(score);

        check=1;
        Intent returnIntent=new Intent();
        returnIntent.putExtra("check",check);
        setResult(Activity.RESULT_OK,returnIntent);
        returnIntent.putExtra("score",score);
        finish();


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

                countDownTimer.cancel();
                check=0;
                Intent returnIntent=new Intent();
                returnIntent.putExtra("check",check);
                setResult(Activity.RESULT_OK,returnIntent);
                returnIntent.putExtra("score",score);
                finish();

            }
        }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.dismiss();
            }
        }).setIcon(android.R.drawable.ic_dialog_alert).show();

    }

    public void nextClick(View view)
    {
        countDownTimer.cancel();

        String answer = quiz_ans.get("" + count);
        if (answer.equalsIgnoreCase(sel_ans))
            score++;
        sel_ans="";
        count++;

        if (count < 11)
        {
//            last_button.setBackgroundColor(getResources().getColor((R.color.colorWhite)));
            if(last_button==optiona)
            {
                last_button.setBackground(getResources().getDrawable(R.drawable.button_quiz1));
            }
            else if(last_button==optionb)
            {
                last_button.setBackground(getResources().getDrawable(R.drawable.button_quiz2));
            }
            else if(last_button==optionc)
            {
                last_button.setBackground(getResources().getDrawable(R.drawable.button_quiz3));
            }
            else if(last_button==optiond)
            {
                last_button.setBackground(getResources().getDrawable(R.drawable.button_quiz4));
            }
            last_button.setTextColor(getResources().getColor(R.color.colorWhite));
            q.setText(quiz.get("" + count));
            arr = quiz_option.get("" + count).split(";");
            for (int i = 0; i < 4; i++)
            {
                optiona.setText("A: "+arr[0]);
                optionb.setText("B: "+arr[1]);
                optionc.setText("c: "+arr[2]);
                optiond.setText("D: "+arr[3]);
            }
            countDownTimer.start();
        }
        else
        {
            check=0;
            rf.child("marks").setValue(score);
            Intent returnIntent=new Intent();
            returnIntent.putExtra("check",check);
            returnIntent.putExtra("score",score);
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        }
    }
}