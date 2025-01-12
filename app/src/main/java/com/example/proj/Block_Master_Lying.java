package com.example.proj;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Block_Master_Lying extends Block_Master {
    private int w,h;//w= wight h = high
    private int lyingnumber;


    private Paint p;

    public Block_Master_Lying(int x, int y, int color,int w, int h, int lyingnumber) {
        super(x, y, color);
        this.w = w;
        this.h = h;
        this.lyingnumber = lyingnumber;
        shapekind = 1;
        p = new Paint();
        p.setColor(color);
        p.setStrokeWidth(5);
    }

    public void BuildBlock(Canvas canvas){
        p.setColor(color);
        canvas.drawRect(x,y,x+w*lyingnumber,y+h,p);
        p.setColor(Color.BLACK);
        for (int i = 0; i <= lyingnumber; i++) {
            canvas.drawLine(x+i*w,y,x+i*w,y+h,p);
        }
        canvas.drawLine(x,y, x + (w * lyingnumber), y,p);
        canvas.drawLine(x,y+h, x + (w * lyingnumber),y+h,p);

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
    public boolean diduserTouchMe(float xu, float yu) {

        return xu >x && xu< (lyingnumber*w)+x && yu>y && yu < y+h;
    }

    public int getLyingnumber() {
        return lyingnumber;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setH(int h) {
        this.h = h;
    }
}
