package com.example.pd4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final static int REQUEST_CODE_1 = 1;
    ListView lv;
    ArrayAdapter aa;
    String[] operation = {"+", "-", "×", "÷"};
    ArrayList<arithmetic> arithmetics;
    ArrayList<results> results;

    int highestAdd = 1;
    int addId = 0;
    int shortestDuration = 11;

    int highestMinus = 1;
    int minusId = 0;
    int minusDuration = 11;

    int highestDivide = 1;
    int divideId = 0;
    int divideDuration = 11;

    int highestMultiply = 1;
    int multiplyId = 0;
    int multiplyDuration = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DBHelper db = new DBHelper(MainActivity.this);

        lv = this.findViewById(R.id.lv);

        arithmetics = db.getOperation();


        for (int i = 0; arithmetics.size() > i; i++) {

            int score = arithmetics.get(i).getScore();
            int id = arithmetics.get(i).getId();
            int duration = arithmetics.get(i).getDuration();
            String operations = arithmetics.get(i).getOperation();

            if (operations.equals("+")) {
                if (duration < shortestDuration) {
                    highestAdd = score;
                    addId = id;
                    shortestDuration = duration;
                }
            } else if (operations.equals("-")) {

                if (duration < minusDuration) {
                    highestMinus = score;
                    minusId = id;
                    minusDuration = duration;
                }
            } else if (operations.equals("÷")) {

                if (duration < divideDuration) {
                    highestDivide = score;
                    divideId = id;
                    divideDuration = duration;
                }
            } else if (operations.equals("×")){

                if (duration < multiplyDuration) {
                    highestMultiply = score;
                    multiplyId = id;
                    multiplyDuration = duration;
                }


            }
        }
        arithmetics = new ArrayList<>();

        arithmetics.add(new arithmetic(addId, "+", shortestDuration, highestAdd));
        arithmetics.add(new arithmetic(minusId, "-", minusDuration, highestMinus));
        arithmetics.add(new arithmetic(multiplyId, "×", multiplyDuration, highestMultiply));
        arithmetics.add(new arithmetic(divideId, "÷", divideDuration, highestDivide));

        aa = new arithmeticAdapter(this, R.layout.row, arithmetics);
        lv.setAdapter(aa);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent i = new Intent(MainActivity.this,
                        showResult.class);

                String target = operation[position];
                i.putExtra("data", target);
                startActivityForResult(i, REQUEST_CODE_1);
            }
        });
    }
}

