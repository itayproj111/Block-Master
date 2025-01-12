package com.example.proj;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Block_Master_Square extends Block_Master {
    private int w,h;//w= wight h = high
    private int squaresize;


    private Paint p;

    public Block_Master_Square(int x, int y, int color,int w, int h, int squaresize) {
        super(x, y, color);
        this.w = w;
        this.h = h;
        this.squaresize = squaresize;
        shapekind=3;
        p = new Paint();
        p.setColor(color);
        p.setStrokeWidth(5);
    }

    public void BuildBlock(Canvas canvas){
        p.setColor(color);
        canvas.drawRect(x,y,x+w*squaresize,y+h*squaresize,p);
        p.setColor(Color.BLACK);
        for (int i = 0; i < squaresize; i++) {
            for (int j = 0; j <= squaresize; j++) {
                canvas.drawLine(x+j*w,y+h*i,x+j*w,y+h*(i+1),p);
            }
            canvas.drawLine(x,y+h*i, x + (w * squaresize), y+h*i,p);
        }
        canvas.drawLine(x,y+h*squaresize, x + (w * squaresize), y+h*squaresize,p);

    }
    public boolean diduserTouchMe(float xu, float yu) {

        return xu >x && xu< (squaresize *w)+x && yu>y && yu < (squaresize*h)+y;
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

    public int getSquaresize() {
        return squaresize;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setH(int h) {
        this.h = h;
    }

}
