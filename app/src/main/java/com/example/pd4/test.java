package com.example.pd4;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class test extends AppCompatActivity {
    TextView tv1stNumber , tv2ndNumber , tvOperationTest , tvPoints;
    Button btnAnswer1 , btnAnswer2 , btnAnswer3, btnAnswer4;
    int correctTotal = 0;
    int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);




        tv1stNumber = findViewById(R.id.tv1stNumber);
        tv2ndNumber = findViewById(R.id.tv2ndNumber);
        tvPoints = findViewById(R.id.tvPoints);
        tvOperationTest = findViewById(R.id.tvOperationTest);
        final View someView = findViewById(R.id.mainView);



        btnAnswer1 = findViewById(R.id.btnAnswer1);
        btnAnswer2 = findViewById(R.id.btnAnswer2);
        btnAnswer3 = findViewById(R.id.btnAnswer3);
        btnAnswer4 = findViewById(R.id.btnAnswer4);

        final Intent intent = getIntent();
        final String target = intent.getStringExtra("data");
        tvOperationTest.setText(target);


        List<Integer> numbers = new ArrayList<>();
        List<Integer> totalBtn = new ArrayList<>(); //numbers that is shown on the buttons

        for (int i = 0; i < 101; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        if(target.equals("+")){

            correctTotal = numbers.get(0) + numbers.get(1);
            totalBtn.add(numbers.get(0) + numbers.get(1));
            totalBtn.add(numbers.get(2) + numbers.get(3));
            totalBtn.add(numbers.get(4) + numbers.get(5));
            totalBtn.add(numbers.get(6) + numbers.get(7));


        }else if(target.equals("-")){

            correctTotal = numbers.get(0) - numbers.get(1);
            totalBtn.add(numbers.get(0) - numbers.get(1));
            totalBtn.add(numbers.get(2) - numbers.get(3));
            totalBtn.add(numbers.get(4) - numbers.get(5));
            totalBtn.add(numbers.get(6) - numbers.get(7));

        }else if(target.equals("×")){

            correctTotal = numbers.get(0) * numbers.get(1);
            totalBtn.add(numbers.get(0) * numbers.get(1));
            totalBtn.add(numbers.get(2) * numbers.get(3));
            totalBtn.add(numbers.get(4) * numbers.get(5));
            totalBtn.add(numbers.get(6) * numbers.get(7));

        }else{

            correctTotal = numbers.get(0) / numbers.get(1);
            totalBtn.add(numbers.get(0) / numbers.get(1));
            totalBtn.add(numbers.get(2) / numbers.get(3));
            totalBtn.add(numbers.get(4) / numbers.get(5));
            totalBtn.add(numbers.get(6) / numbers.get(7));

        }

        Collections.shuffle(totalBtn);

        tv1stNumber.setText(Integer.toString(numbers.get(0)));
        tv2ndNumber.setText(Integer.toString(numbers.get(1)));

        btnAnswer1.setText(Integer.toString(totalBtn.get(0)));
        btnAnswer2.setText(Integer.toString(totalBtn.get(1)));
        btnAnswer3.setText(Integer.toString(totalBtn.get(2)));
        btnAnswer4.setText(Integer.toString(totalBtn.get(3)));



        btnAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int test1 = Integer.parseInt(btnAnswer1.getText().toString());
                if(test1 == correctTotal){
                    someView.setBackgroundColor(Color.GREEN);
                    score += 1;

                }else{
                    someView.setBackgroundColor(Color.RED);

                }
                tvPoints.setText("Points : " + score);
                finish();
                startActivity(getIntent());

            }
        });

        btnAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int test1 = Integer.parseInt(btnAnswer2.getText().toString());
                if(test1 == correctTotal){
                    someView.setBackgroundColor(Color.GREEN);
                    score += 1;
                }else{
                    someView.setBackgroundColor(Color.RED);

                }

                finish();
                startActivity(getIntent());

            }
        });
        btnAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int test1 = Integer.parseInt(btnAnswer3.getText().toString());
                if(test1 == correctTotal){
                    someView.setBackgroundColor(Color.GREEN);
                    score += 1;
                }else{
                    someView.setBackgroundColor(Color.RED);

                }

                finish();
                startActivity(getIntent());
            }
        });
        btnAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int test1 = Integer.parseInt(btnAnswer4.getText().toString());
                if(test1 == correctTotal){
                    someView.setBackgroundColor(Color.GREEN);
                    score += 1;
                }else{
                    someView.setBackgroundColor(Color.RED);

                }
                finish();
                startActivity(getIntent());

            }
        });





    }
}
