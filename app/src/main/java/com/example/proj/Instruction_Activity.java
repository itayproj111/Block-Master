package com.example.proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

public class Instruction_Activity extends AppCompatActivity {
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instraction);
        linearLayout = findViewById(R.id.activity_instruction);
        Intent i = getIntent();
        String BackgroundColor = i.getStringExtra("color");
        setBackgroundColor(BackgroundColor);
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
            //case "Default":{
                //linearLayout.setBackgroundResource(R.drawable.img3);
                //break;
            //}
            default:
                break;

        }
    }
}