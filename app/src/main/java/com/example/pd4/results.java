package com.example.pd4;

import java.io.Serializable;

public class results implements Serializable {

    private int duration ;
    private int score;

    public results(int duration , int score){

        this.duration = duration;
        this.score = score;
    }



    public int getDuration(){
        return duration;
    }
    public int getScore(){
        return score;
    }

}
