package com.example.proj;

import android.graphics.Canvas;

public abstract class Block_Master {
    protected int x,y,color;
    protected int shapekind;
    private boolean isused;// בודק אם הבלוק ממוקם במשחק או לא

    public Block_Master(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
        isused = false;
    }

    public int getShapekind() {
        return shapekind;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isIsused() {
        return isused;
    }

    public void setIsused(boolean isused) {
        this.isused = isused;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setColor(int color) {
        this.color = color;
    }

    abstract void BuildBlock(Canvas canvas);
    abstract void resetsizeblocktosmall(int smallsize);
    abstract void resetsizeblocktonormal(int smallsize);
    abstract boolean diduserTouchMe(float xu, float yu);

    public int getColor() {
        return color;
    }
}
