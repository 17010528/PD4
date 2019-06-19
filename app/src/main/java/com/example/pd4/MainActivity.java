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
    ListView lv;
    ArrayAdapter aa;
    String[] operation = {"+" , "-" , "×" , "÷"};
    ArrayList<arithmetic> arithmetics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = this.findViewById(R.id.lv);
        arithmetics = new ArrayList<>();
        arithmetics.add(new arithmetic("+",23,2));
        arithmetics.add(new arithmetic("-",23,2));
        arithmetics.add(new arithmetic("×",23,2));
        arithmetics.add(new arithmetic("÷",23,2));

        aa = new arithmeticAdapter(this, R.layout.row, arithmetics);
        lv.setAdapter(aa);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                Intent i = new Intent(MainActivity.this,
                        showResult.class);

                String target = operation[position];
                i.putExtra("data", target);
                startActivity(i);
            }
        });
    }
}
