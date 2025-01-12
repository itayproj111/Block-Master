package com.example.proj;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Block_Master_Stand extends Block_Master {
    private int w,h;//w= wight h = high
    private int standingnumber;


    private Paint p;

    public Block_Master_Stand(int x, int y, int color,int w, int h, int standingnumber) {
        super(x, y, color);
        this.w = w;
        this.h = h;
        shapekind = 2;
        this.standingnumber = standingnumber;
        p = new Paint();
        p.setColor(color);
        p.setStrokeWidth(5);
    }

    public void BuildBlock(Canvas canvas){
        p.setColor(color);
        canvas.drawRect(x,y,x+w,y+h*standingnumber,p);
        p.setColor(Color.BLACK);
        for (int i = 0; i <= standingnumber; i++) {
            canvas.drawLine(x,y+i*h,x+w,y+i*h,p);
        }
        canvas.drawLine(x,y, x,y+ (h * standingnumber),p);
        canvas.drawLine(x+w,y, x+w,y+ (h * standingnumber),p);

    }
    public boolean diduserTouchMe(float xu, float yu) {

        return xu >x && xu< x+w && yu>y && yu < (standingnumber*h)+y;
    }
    public void resetsizeblocktosmall(int smallsize){
        w = w/smallsize;
        h = h/smallsize;
        p.setStrokeWidth(10/smallsize);

    }
    public void resetsizeblocktonormal(int smallsize){
        w = w*smallsize;
        h = h*smallsize;
        p.setStrokeWidth(10);
    }

    public int getStandingnumber() {
        return standingnumber;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setH(int h) {
        this.h = h;
    }
}
