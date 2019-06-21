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
    private static final int DATABASE_VER = 11;
    private static final String TABLE_GAME = "PD4";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_OPERATION = "operation";
    private static final String COLUMN_DURATION = "duration";
    private static final String COLUMN_SCORE = "score";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_GAME +  "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_DURATION + " INTEGER,"
                + COLUMN_OPERATION + " TEXT,"
                + COLUMN_SCORE + " INTEGER)";
        db.execSQL(createTableSql);
        Log.i("info" ,"created tables");

        ContentValues values = new ContentValues();

        values.put(COLUMN_ID , 1);
        values.put(COLUMN_OPERATION, "+");
        values.put(COLUMN_DURATION,3);
        values.put(COLUMN_SCORE, 2);

        db.insert(TABLE_GAME , null , values);

        values.put(COLUMN_ID , 5);
        values.put(COLUMN_OPERATION, "+");
        values.put(COLUMN_DURATION,2);
        values.put(COLUMN_SCORE, 3);

        db.insert(TABLE_GAME , null , values);

        values.put(COLUMN_ID , 2);
        values.put(COLUMN_OPERATION, "-");
        values.put(COLUMN_DURATION,5);
        values.put(COLUMN_SCORE, 4);
        db.insert(TABLE_GAME , null , values);

        values.put(COLUMN_ID , 3);
        values.put(COLUMN_OPERATION, "×");
        values.put(COLUMN_DURATION,7);
        values.put(COLUMN_SCORE, 6);
        db.insert(TABLE_GAME , null , values);

        values.put(COLUMN_ID , 4);
        values.put(COLUMN_OPERATION, "÷");
        values.put(COLUMN_DURATION,9);
        values.put(COLUMN_SCORE, 8);
        db.insert(TABLE_GAME , null , values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAME);
        // Create table(s) again
        onCreate(db);

    }

    public void insertGame(String operation, int duration , int score){

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        values.put(COLUMN_OPERATION, operation);
        // Store the column name as key and the description as value
        values.put(COLUMN_DURATION, duration);
        // Store the column name as key and the date as value
        values.put(COLUMN_SCORE, score);

        // Insert the row into the TABLE_TASK

        db.insert(TABLE_GAME, null, values);
        // Close the database connection
        db.close();
    }
    public void updateGame(int id , String operation, int duration , int score ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_OPERATION, operation);
        values.put(COLUMN_DURATION, duration);
        values.put(COLUMN_SCORE, score);

        int result = db.update(TABLE_GAME, values, "_id="+id, null);
        if (result == -1){
            Log.d("DBHelper", "Update failed");
        }else{
            Log.d("DBHelper" , Integer.toString(duration) );
        }

        db.close();

    }




//    public void updateEvent(int id , String title, String description , String date , String time){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_TITLE, title);
//        values.put(COLUMN_DESCRIPTION , description);
//        values.put(COLUMN_DATE, date);
//        values.put(COLUMN_TIME ,time);
//
//        int result = db.update(TABLE_DETAILS, values, "_id="+id, null);
//        if (result == -1){
//            Log.d("DBHelper", "Update failed");
//        }
//
//        db.close();
//
//    }


    public ArrayList<arithmetic> getAllOperations(){
        ArrayList<arithmetic> arithmetics = new ArrayList<arithmetic>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID , COLUMN_OPERATION  , COLUMN_DURATION, COLUMN_SCORE};
        Cursor cursor = db.query(TABLE_GAME , columns , null , null ,null , null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String operation= cursor.getString(1);
                int duration = cursor.getInt(2);
                int score = cursor.getInt(3);
                arithmetic obj = new arithmetic(id,operation , duration , score);
                arithmetics.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return arithmetics;
    }


}

