package com.example.pd4;

import java.io.Serializable;

public class arithmetic implements Serializable {

    private String operation;
    private int duration;
    private int score;

    public arithmetic(String operation , int duration , int score){
        this.operation = operation;
        this.duration = duration;
        this.score = score;
    }



    public String getOperation(){
        return operation;
    }
    public int getDuration(){
        return duration;
    }
    public int getScore(){
        return score;
    }
}
