package com.example.proj;

import static com.example.proj.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

public class Settings_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private Spinner spinner;
    private String [] arrcolor = {"","Default", "Red","Blue","Green","Yellow","White","Pink"};
    private boolean isfirsttime = true;
    LinearLayout linearLayout;
    private boolean isPlay;
    private Button btnPlay,btnSave;
    private ImageButton btnSong;
    private Intent serviceIntent;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_setting);

        spinner=findViewById(id.spinner);
        spinner.setOnItemSelectedListener(this);
        linearLayout = findViewById(id.activity_settings);

        isPlay = false;
        btnSong = findViewById(id.btnSong);
        btnSave = findViewById(id.btnSave);
        btnPlay = findViewById(id.btnStart);
        btnPlay.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnSong.setOnClickListener(this);
        serviceIntent = new Intent(this, PlayService.class);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrcolor);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter((SpinnerAdapter) aa);
        Intent i = getIntent();
        String BackgroundColor = i.getStringExtra("color");
        setBackgroundColor(BackgroundColor);

    }
    @Override
    public void onClick(View v) {
        if(btnSave==v){
            finish();
        }
        else {
            if(!isPlay)
            {
                isPlay = true;
                btnPlay.setText("Stop");
                btnSong.setBackgroundResource(drawable.stopsong);
                startService(serviceIntent);
            }
            else
            {
                isPlay = false;
                btnPlay.setText("Play");
                btnSong.setBackgroundResource(drawable.song);
                stopService(serviceIntent);
            }
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(isfirsttime==false){
            Intent intent = new Intent();
            intent.putExtra("color",arrcolor[position]);
            setResult(RESULT_OK,intent);
        }
        isfirsttime= false;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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