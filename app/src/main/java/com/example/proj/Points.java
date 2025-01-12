package com.example.proj;


public class Points {
    private int points, combo;

    public Points() {
        this.points = 0;
        this.combo = 0;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getCombo() {
        return combo;
    }

    public void resetCombo() {
        this.combo = 0;
    }
    public void changeCombo(int num){
        combo= combo+num;//מספר השורות והטורים שהשלימו במהלך אחד = Num
    }
}
