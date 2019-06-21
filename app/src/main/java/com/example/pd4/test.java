package com.example.pd4;

import android.content.Intent;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class test extends AppCompatActivity {
    TextView tv1stNumber , tv2ndNumber , tvOperationTest, tvWarning ;
    Button btnAnswer1 , btnAnswer2 , btnAnswer3, btnAnswer4 ;
    int correctTotal = 0;

    boolean running;

    ArrayList<results> results;
    ArrayList<arithmetic> arithmetics;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);



        final Chronometer chronometer = findViewById(R.id.chronometer);
        tv1stNumber = findViewById(R.id.tv1stNumber);
        tv2ndNumber = findViewById(R.id.tv2ndNumber);
        tvWarning = findViewById(R.id.tvWarning);

        tvOperationTest = findViewById(R.id.tvOperationTest);
        final View someView = findViewById(R.id.mainView);


        btnAnswer1 = findViewById(R.id.btnAnswer1);
        btnAnswer2 = findViewById(R.id.btnAnswer2);
        btnAnswer3 = findViewById(R.id.btnAnswer3);
        btnAnswer4 = findViewById(R.id.btnAnswer4);

        tvWarning.setVisibility(View.GONE);

        if(!running){
            chronometer.start();
            running = true;
        }


        final Intent intent = getIntent();
        String[] target1 = intent.getStringArrayExtra("data");

        final int id = Integer.parseInt(target1[0]);


        final DBHelper dbh = new DBHelper(test.this);
        arithmetics = dbh.getAllOperations();
        final String target = arithmetics.get(id).getOperation();




        tvOperationTest.setText(target);


        List<Integer> numbers = new ArrayList<>();
        List<Integer> totalBtn = new ArrayList<>(); //numbers that is shown on the buttons

        for (int i = 10; i < 101; i++) { //getting numbers 10 - 100
            numbers.add(i);
        }

        Collections.shuffle(numbers);
        List<Integer> number = new ArrayList<>();

        for(int i = 0 ; i < 8 ; i++){  //getting the first 6 random numbers
            number.add(numbers.get(i));
        }

        List<Integer> division = new ArrayList<>();
        for(int i = 2 ; i < 6 ; i++){
            division.add(i);
        }

        Collections.shuffle(division);
        Collections.sort(number);
        Collections.reverse(number);

        if(target.equals("+")){

            correctTotal = number.get(0) + number.get(1);
            totalBtn.add(number.get(0) + number.get(1));
            totalBtn.add(number.get(2) + number.get(3));
            totalBtn.add(number.get(4) + number.get(5));
            totalBtn.add(number.get(6) + number.get(7));

            Collections.shuffle(totalBtn);

            tv1stNumber.setText(Integer.toString(number.get(0)));
            tv2ndNumber.setText(Integer.toString(number.get(1)));

            btnAnswer1.setText(Integer.toString(totalBtn.get(0)));
            btnAnswer2.setText(Integer.toString(totalBtn.get(1)));
            btnAnswer3.setText(Integer.toString(totalBtn.get(2)));
            btnAnswer4.setText(Integer.toString(totalBtn.get(3)));

        }else if(target.equals("-")){

            correctTotal = number.get(0) - number.get(1);
            totalBtn.add(number.get(0) - number.get(1));
            totalBtn.add(number.get(2) - number.get(3));
            totalBtn.add(number.get(4) - number.get(5));
            totalBtn.add(number.get(6) - number.get(7));

            Collections.shuffle(totalBtn);

            tv1stNumber.setText(Integer.toString(number.get(0)));
            tv2ndNumber.setText(Integer.toString(number.get(1)));

            btnAnswer1.setText(Integer.toString(totalBtn.get(0)));
            btnAnswer2.setText(Integer.toString(totalBtn.get(1)));
            btnAnswer3.setText(Integer.toString(totalBtn.get(2)));
            btnAnswer4.setText(Integer.toString(totalBtn.get(3)));
        }else if(target.equals("×")){

            correctTotal = number.get(0) * number.get(1);
            totalBtn.add(number.get(0) * number.get(1));
            totalBtn.add(number.get(2) * number.get(3));
            totalBtn.add(number.get(4) * number.get(5));
            totalBtn.add(number.get(6) * number.get(7));
            Collections.shuffle(totalBtn);

            tv1stNumber.setText(Integer.toString(number.get(0)));
            tv2ndNumber.setText(Integer.toString(number.get(1)));

            btnAnswer1.setText(Integer.toString(totalBtn.get(0)));
            btnAnswer2.setText(Integer.toString(totalBtn.get(1)));
            btnAnswer3.setText(Integer.toString(totalBtn.get(2)));
            btnAnswer4.setText(Integer.toString(totalBtn.get(3)));
        }else{

            tvWarning.setVisibility(View.VISIBLE);
            correctTotal = number.get(0) / division.get(1);
            totalBtn.add(number.get(0) / division.get(0));
            totalBtn.add(number.get(0) / division.get(1));
            totalBtn.add(number.get(0) / division.get(2));
            totalBtn.add(number.get(0) / division.get(3));
            Collections.shuffle(totalBtn);

            tv1stNumber.setText(Integer.toString(number.get(0)));
            tv2ndNumber.setText(Integer.toString(division.get(1)));

            btnAnswer1.setText(Integer.toString(totalBtn.get(0)));
            btnAnswer2.setText(Integer.toString(totalBtn.get(1)));
            btnAnswer3.setText(Integer.toString(totalBtn.get(2)));
            btnAnswer4.setText(Integer.toString(totalBtn.get(3)));

        }





        btnAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int test1 = Integer.parseInt(btnAnswer1.getText().toString());

                int score = arithmetics.get(id).getScore();
                int duration = arithmetics.get(id).getDuration();

                if(test1 == correctTotal){

                    someView.setBackgroundColor(Color.GREEN);
                    score = score + 1;

                }else{

                    someView.setBackgroundColor(Color.RED);

                }
                if(running){
                    chronometer.stop();
                }

                String timer = chronometer.getText().toString();
                String[] splitDuration = timer.split(":");
                String minutes = splitDuration[0];
                String seconds = splitDuration[1];



                int minute = Integer.parseInt(minutes);
                int second = Integer.parseInt(seconds);



                for(int i = 0 ; minute > i ; i++){
                    second = minute * 60 + second;
                }



                duration = duration + second ;

                Toast.makeText(test.this, "Duration :" + duration +" Seconds\nPoints : "+ score ,
                        Toast.LENGTH_SHORT).show();
                dbh.updateGame(id , target , duration , score);
                dbh.close();
                finish();
                startActivity(getIntent());

            }
        });

        btnAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int score = arithmetics.get(id).getScore();
                int duration = arithmetics.get(id).getDuration();

                int test1 = Integer.parseInt(btnAnswer2.getText().toString());
                if(test1 == correctTotal){
                    someView.setBackgroundColor(Color.GREEN);
                    score += 1;
                }else{
                    someView.setBackgroundColor(Color.RED);

                }

                if(running){
                    chronometer.stop();
                }

                String timer = chronometer.getText().toString();
                String[] splitDuration = timer.split(":");
                String minutes = splitDuration[0];
                String seconds = splitDuration[1];



                int minute = Integer.parseInt(minutes);
                int second = Integer.parseInt(seconds);



                for(int i = 0 ; minute > i ; i++){
                    second = minute * 60 + second;
                }



                duration = duration + second ;

                Toast.makeText(test.this, "Duration :" + duration +" Seconds\nPoints : "+score,
                        Toast.LENGTH_SHORT).show();
                dbh.updateGame(id , target , duration , score);
                dbh.close();
                finish();
                startActivity(getIntent());

            }
        });
        btnAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int score = arithmetics.get(id).getScore();
                int duration = arithmetics.get(id).getDuration();

                int test1 = Integer.parseInt(btnAnswer3.getText().toString());
                if(test1 == correctTotal){
                    someView.setBackgroundColor(Color.GREEN);
                    score += 1;
                }else{
                    someView.setBackgroundColor(Color.RED);

                }

                if(running){
                    chronometer.stop();
                }

                String timer = chronometer.getText().toString();
                String[] splitDuration = timer.split(":");
                String minutes = splitDuration[0];
                String seconds = splitDuration[1];



                int minute = Integer.parseInt(minutes);
                int second = Integer.parseInt(seconds);



                for(int i = 0 ; minute > i ; i++){
                    second = minute * 60 + second;
                }

                duration  = duration + second ;

                Toast.makeText(test.this, "Duration :" + duration +" Seconds\nPoints : "+ score,
                        Toast.LENGTH_SHORT).show();
                dbh.updateGame(id , target , duration , score);
                dbh.close();
                finish();
                startActivity(getIntent());
            }
        });
        btnAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int score = arithmetics.get(id).getScore();
                int duration = arithmetics.get(id).getDuration();

                int test1 = Integer.parseInt(btnAnswer4.getText().toString());
                if(test1 == correctTotal){
                    someView.setBackgroundColor(Color.GREEN);
                    score += 1;
                }else{
                    someView.setBackgroundColor(Color.RED);

                }

                if(running){
                    chronometer.stop();
                }

                String timer = chronometer.getText().toString();
                String[] splitDuration = timer.split(":");
                String minutes = splitDuration[0];
                String seconds = splitDuration[1];



                int minute = Integer.parseInt(minutes);
                int second = Integer.parseInt(seconds);



                for(int i = 0 ; minute > i ; i++){
                    second = minute * 60 + second;
                }



                duration = duration + second ;

                Toast.makeText(test.this, "Duration :" + duration +" Seconds\nPoints : "+score,
                        Toast.LENGTH_SHORT).show();
                dbh.updateGame(id , target , duration , score);
                dbh.close();
                finish();
                startActivity(getIntent());

            }
        });


    }

}
