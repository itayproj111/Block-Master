package com.example.proj;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Game_Activity extends AppCompatActivity {
    private BoardGame boardGame;
    private TextView points, bestscore;
    private Points pointsTotal;
    private LinearLayout linearLayout;
    private String backgroundColor;
    FbModule fbModule;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        boardGame = new BoardGame(this);
        LinearLayout ll = findViewById(R.id.Lin);
        ll.addView(boardGame);
        pointsTotal= new Points();
        points = findViewById(R.id.points);
        bestscore = findViewById(R.id.bestscore);
        linearLayout = findViewById(R.id.activity_game);
        Intent i = getIntent();
        backgroundColor = i.getStringExtra("color");
        setBackgroundColor(backgroundColor);


    }
    public String getBackgroundColor(){
        return backgroundColor;
    }

    public void reset() {
        //BoardGame boardGame = new BoardGame(this);
        //setContentView(boardGame);
        boardGame.reset();
    }

    public void continue1() {
    }

    private void setBackgroundColor(String backgroundColor) {
        switch (backgroundColor)
        {
            case "Blue":
            {
                linearLayout.setBackgroundColor(Color.BLUE);
                break;
            }
            case "Red":
            {
                linearLayout.setBackgroundColor(Color.RED);
                break;
            }
            case "Yellow":
            {
                linearLayout.setBackgroundColor(Color.YELLOW);
                break;
            }
            case "Green":
            {
                linearLayout.setBackgroundColor(Color.GREEN);
                break;
            }
            case "White":
            {
                linearLayout.setBackgroundColor(Color.WHITE);
                break;
            }
            case "Pink":
            {
                linearLayout.setBackgroundColor(Color.argb(255,255,182,193));
                break;
            }
            default:
                break;

        }
    }
}