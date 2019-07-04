package com.example.pd4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.view.View.GONE;


public class showResult extends AppCompatActivity {
    private final static int REQUEST_CODE_2 = 2;

    ListView lv;
    ArrayAdapter aa;
    ArrayList<results> results;
    ArrayList<arithmetic> arithmetics;

//    int[] score = { 5 , 2 , 3 , 4 ,5};
//    int[] duration = { 11 , 12 , 13 , 14 ,15};

    TextView tvOperation, tvOperationName, tvEmpty;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);

        View view = findViewById(R.id.resultsView);
        View root = view.getRootView();

        tvOperation = findViewById(R.id.tvOperation);
        tvOperationName = findViewById(R.id.tvOperationName);
        tvEmpty = findViewById(R.id.tvEmpty);


        btnStart = findViewById(R.id.btnStart);


        lv = this.findViewById(R.id.lv1);

        lv.setVisibility(View.GONE);


        Intent intent = getIntent();
        final String target = intent.getStringExtra("data");

        DBHelper db = new DBHelper(showResult.this);
        arithmetics = db.getOperation();
        results = new ArrayList<>();
        Log.e("Size : " , Integer.toString(arithmetics.size()));
        for (int i = 0; arithmetics.size() > i; i++) {

            if (arithmetics.get(i).getOperation().equals(target)) {

                int duration = arithmetics.get(i).getDuration();
                int score = arithmetics.get(i).getScore();
                String operation = arithmetics.get(i).getOperation();
                int id = arithmetics.get(i).getId();
                if(score == 1){

                    tvEmpty.setVisibility(View.GONE);
                    lv.setVisibility(View.VISIBLE);
                    results.add(new results(id, operation, duration, score));
                    aa = new resultsAdapter(this, R.layout.row_results, results);
                    lv.setAdapter(aa);
                }


            }
        }


        tvOperation.setText(target);

        if (target.equals("+")) {

            tvOperationName.setText("Add");
            root.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));

        } else if (target.equals("-")) {

            tvOperationName.setText("Subtract");
            root.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));


        } else if (target.equals("×")) {

            tvOperationName.setText("Multiply");
            root.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));


        } else {

            tvOperationName.setText("Divide");
            root.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));


        }

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHelper dbh = new DBHelper(showResult.this);
                dbh.insertGame(target, 0, 0);
                arithmetics = dbh.getTestOperation();

                for (int i = 0; arithmetics.size() > i; i++) {
                    if (arithmetics.size() - 1 == i) {
                        Intent intent = new Intent(showResult.this, test.class);
                        int id = arithmetics.get(i).getId();
                        String[] data = {Integer.toString(id)};
                        intent.putExtra("data", data);
                        startActivityForResult(intent, REQUEST_CODE_2);
                    }
                }

                dbh.close();


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent dataIntent) {
        super.onActivityResult(requestCode, resultCode, dataIntent);

        DBHelper db = new DBHelper(showResult.this);

        Intent intent = getIntent();
        final String target = intent.getStringExtra("data");

        arithmetics.clear();
        arithmetics = db.getOperation();
        results = new ArrayList<>();
        for (int i = 0; arithmetics.size() > i; i++) {

            if (arithmetics.get(i).getOperation().equals(target)) {

                int duration = arithmetics.get(i).getDuration();
                int score = arithmetics.get(i).getScore();
                String operation = arithmetics.get(i).getOperation();
                int id = arithmetics.get(i).getId();

                if(score == 0) {

                }else if(score == 0 && duration == 0) {


                }else{
                    results.add(new results(id, operation, duration, score));
                    aa = new resultsAdapter(this, R.layout.row_results, results);
                    lv.setAdapter(aa);
                }
            }
        }
    }
}
