package com.example.pd4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class resultsAdapter extends ArrayAdapter {
    private ArrayList<results> results;
    private Context context;
    private TextView tvDuration, tvScore, tvOpeartion;

    public resultsAdapter(Context context, int resource, ArrayList<results> objects) {
        super(context, resource, objects);
        // Store the food that is passed to this adapter
        results = objects;
        // Store Context object as we would need to use it later
        this.context = context;
    }

    // getView() is the method ListView will call to get the
    //  View object every time ListView needs a row
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // The usual way to get the LayoutInflater object to
        //  "inflate" the XML file into a View object
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // "Inflate" the row.xml as the layout for the View object
        View rowView = inflater.inflate(R.layout.row_results, parent, false);

        // Get the TextView object
        tvDuration = rowView.findViewById(R.id.tvDuration1);
        // Get the ImageView object

        tvScore = rowView.findViewById(R.id.tvScore1);


        // The parameter "position" is the index of the
        //  row ListView is requesting.
        //  We get back the food at the same index.
        results currentPark = results.get(position);
        // Set the TextView to show the food

        tvDuration.setText("Duration : "+currentPark.getDuration() + " seconds");
        tvScore.setText("High-score : " +currentPark.getScore() + "/5");
        // Set the image to star or nostar accordingly

        // Return the nicely done up View to the ListView
        return rowView;
    }
}
