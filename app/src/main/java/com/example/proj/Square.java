package com.example.proj;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Square {

    private int x,y,color;
    private int w,h;//w= wight h = high
    private Paint p;

    public Square(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.color = Color.rgb(135, 206, 250);
        this.w = w;
        this.h = h;
        p = new Paint();
        p.setColor(color);
    }
    public void draw(Canvas canvas){

        canvas.drawRect(x,y,x+w,y+h,p);
    }
    public boolean diduserTouchMe(int xu, int yu) {

        return xu >x && xu< x+w && yu>y && yu < y+h;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
}
