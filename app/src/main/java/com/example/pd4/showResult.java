package com.example.pd4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;



public class showResult extends AppCompatActivity {

    ListView lv;
    ArrayAdapter aa;
    ArrayList<results> results;

    int[] score = { 5 , 2 , 3 , 4 ,5};
    int[] duration = { 11 , 12 , 13 , 14 ,15};

    TextView tvOperation , tvOperationName;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);

        tvOperation = findViewById(R.id.tvOperation);
        tvOperationName = findViewById(R.id.tvOperationName);


        btnStart = findViewById(R.id.btnStart);


        lv = this.findViewById(R.id.lv1);
        results = new ArrayList<>();
        Arrays.sort(score);

        for(int i = 0 ; score.length > i ; i++){
            results.add(new results(score[i],duration[i]));
        }

        aa = new resultsAdapter(this, R.layout.row_results, results);
        lv.setAdapter(aa);

        Intent intent = getIntent();
        final String target = intent.getStringExtra("data");
        tvOperation.setText(target);

        if(target.equals("+")){

            tvOperationName.setText("Add");

        }else if(target.equals("-")){

            tvOperationName.setText("Subtract");

        }else if(target.equals("×")){

            tvOperationName.setText("Multiply");

        }else{

            tvOperationName.setText("Divide");

        }

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(showResult.this, test.class);
                intent.putExtra("data", target);
                startActivity(intent);
            }
        });

    }
}
