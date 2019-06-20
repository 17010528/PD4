package com.example.pd4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PD4.db";
    private static final int DATABASE_VER = 1;
    private static final String TABLE_DETAILS = "PD4";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_DURATION = "duration";
    private static final String COLUMN_SCORE = "score";
    private static final String COLUMN_OPERATION = "operation";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_DETAILS +  "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_DURATION + " INTEGER,"
                + COLUMN_OPERATION + " TEXT,"
                + COLUMN_SCORE + " INTEGER)";
        db.execSQL(createTableSql);
        Log.i("info" ,"created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DETAILS);
        // Create table(s) again
        onCreate(db);

    }

    public void insertDetails(String title, String description, String date , String time){

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        // Store the column name as key and the description as value
        values.put(COLUMN_DESCRIPTION, description);
        // Store the column name as key and the date as value
        values.put(COLUMN_DATE, date);

        values.put(COLUMN_TIME , time);

        // Insert the row into the TABLE_TASK

        db.insert(TABLE_DETAILS, null, values);
        // Close the database connection
        db.close();
    }


    public String getDeleteDetails(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        String msg = "";
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_DETAILS, condition, args);
        if(result==-1){
            msg = "unsuccessful";
        }else{
            msg = "successful";
        }
        return msg;
    }


    public void updateEvent(int id , String title, String description , String date , String time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_DESCRIPTION , description);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_TIME ,time);

        int result = db.update(TABLE_DETAILS, values, "_id="+id, null);
        if (result == -1){
            Log.d("DBHelper", "Update failed");
        }

        db.close();

    }


    public ArrayList<deleteDetails> getDelete()
    {
        ArrayList<deleteDetails> deleteDetails = new ArrayList<deleteDetails>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID  , COLUMN_DATE , COLUMN_TIME};

        Cursor cursor = db.query(TABLE_DETAILS , columns , null , null , null, null ,null  ,null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String date = cursor.getString(1);
                String time = cursor.getString(2);
                deleteDetails obj = new deleteDetails(id, date , time);
                deleteDetails.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return deleteDetails;
    }


    public ArrayList<details> getSomeDetails(){
        ArrayList<details> details = new ArrayList<details>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_TITLE  , COLUMN_DATE , COLUMN_TIME};
        Cursor cursor = db.query(TABLE_DETAILS , columns , null , null ,COLUMN_ID , null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String title= cursor.getString(0);
                String date = cursor.getString(1);
                String time = cursor.getString(2);
                details obj = new details(title, date , time);
                details.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return details;
    }

    public ArrayList<allDetails> getAllDetails(int position){
        ArrayList<allDetails> allDetails = new ArrayList<allDetails>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID , COLUMN_TITLE  , COLUMN_DESCRIPTION, COLUMN_DATE , COLUMN_TIME};
        Cursor cursor = db.query(TABLE_DETAILS , columns , null , null ,null , null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title= cursor.getString(1);
                String description = cursor.getString(2);
                String date = cursor.getString(3);
                String time = cursor.getString(4);
                allDetails obj = new allDetails(id,title,description, date , time);
                allDetails.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return allDetails;
    }



}

