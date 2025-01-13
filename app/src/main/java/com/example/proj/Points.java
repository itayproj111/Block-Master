package com.example.proj;


import android.content.Context;
import android.widget.Toast;

public class Points {
    private int points,bestpoints, combo,count;

    public Points() {
        this.points = 0;
        this.bestpoints = 0;
        this.combo = 1;
        this.count=0;
    }
    public void updatecount(Context context){
        if (count==3&&combo>1){
            combo=1;
            count=0;
            Toast.makeText(context, "reset combo", Toast.LENGTH_SHORT).show();
        }
        count++;
    }

    public int getPoints() {
        return points;
    }

    public void addonepoint() {
        points++;
    }

    public void updatepoints(Context context) {
        if (combo>10){
            points = points + combo*12;
            Toast.makeText(context, "combo is "+ combo, Toast.LENGTH_SHORT).show();
            combo++;
        }
        else{
            points = points + combo*8;
            Toast.makeText(context, "combo is "+ combo, Toast.LENGTH_SHORT).show();
            combo++;
        }
    }

    public int getBestscore() {
        return bestpoints;
    }

    public void updateBestscore() {
        bestpoints = points;
    }

    public void resetcount() {
        count= 0;
    }
}
