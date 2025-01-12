package com.example.proj;

public class Board {//מערך דו ממדי של הלוח
    private int [][] arr;

    public Board(int length) {
        this.arr = new int[length][length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                arr[i][j]=0;//0 משבצת פנויה 1 משבת תפוסה
            }
        }
    }

    public boolean checkisEmpty(int i , int j){
        return arr[i][j]==0;
    }

    public void changeblockstatus(int i,int j,int stats){
        arr[i][j]=stats;
    }
    public boolean isrowfull(int i){
        for (int k = 0; k < arr.length; k++) {
            if (arr[i][k]==0)
                return false;
        }
        return true;
    }
    public boolean islinefull(int j){
        for (int k = 0; k < arr.length; k++) {
            if (arr[k][j]==0)
                return false;
        }
        return true;
    }

    public void cleanline(int i) {
        for (int k = 0; k < arr.length; k++) {
            arr[k][i]=0;
        }
    }

    public void cleanrow(int i) {
        for (int k = 0; k < arr.length; k++) {
            arr[i][k]=0;
        }
    }
}
