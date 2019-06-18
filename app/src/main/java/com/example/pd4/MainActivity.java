package com.example.pd4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayAdapter aa;
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
    }
}
