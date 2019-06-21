package com.example.pd4;

import java.io.Serializable;

public class results implements Serializable {
    private int id;
    private String operation;
    private int duration ;
    private int score;


    public results(int id, String operation, int duration , int score){
        this.id = id;
        this.operation = operation;
        this.duration = duration;
        this.score = score;
    }



    public int getDuration(){
        return duration;
    }
    public int getScore(){
        return score;
    }
    public int getId(){
        return id;
    }
    public String getOperation(){
        return operation;
    }
}
