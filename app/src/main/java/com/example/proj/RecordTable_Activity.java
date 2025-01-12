package com.example.proj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class RecordTable_Activity extends AppCompatActivity {
    private ArrayList<Record> recordsList;
    private  LinearLayout linearLayout;
    private RecordAdapter adapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_table);
        linearLayout= findViewById(R.id.activity_record_table);

        Intent i = getIntent();
        String BackgroundColor = i.getStringExtra("color");
        recordsList = i.getParcelableExtra("recordlist");
        setBackgroundColor(BackgroundColor);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // to be vertical


        adapter = new RecordAdapter(this, recordsList);
        recyclerView.setAdapter(adapter);

    }
    public void dataChange() {
        // update the RecyclerView after change in the arraylist
        adapter.notifyDataSetChanged();
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