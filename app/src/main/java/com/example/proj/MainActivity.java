package com.example.proj;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnStartGame,btnInstruction,btnRecord,btnsettings;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private String BackgroundColor = "Default";
    private FbModule fbModule;
    private Button btnLogout;

    private LinearLayout linearLayout;
    private ArrayList<Record> recordlist;
    private Points points;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode()==RESULT_OK){
                            Intent data = result.getData();
                            BackgroundColor = data.getStringExtra("color");
                            fbModule.changeBackgroundColorInFireBase(BackgroundColor);//שומר בFIREBASE
                            Toast.makeText(MainActivity.this, BackgroundColor, Toast.LENGTH_SHORT).show();                        }
                    }
                });
    }
    private void init(){
        recordlist = new ArrayList<>();
        btnInstruction = findViewById(R.id.btnInstruction);
        btnRecord = findViewById(R.id.btnRecord);
        btnsettings = findViewById(R.id.btnsettings);
        btnStartGame = findViewById(R.id.btnStartGame);
        btnsettings.setOnClickListener(this);
        btnRecord.setOnClickListener(this);
        btnStartGame.setOnClickListener(this);
        btnInstruction.setOnClickListener(this);
        linearLayout = findViewById(R.id.main);
        fbModule = new FbModule(this,recordlist);
        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v == btnStartGame){
            Intent intent = new Intent(this, Game_Activity.class);
            intent.putExtra("color",BackgroundColor);
            intent.putExtra("recordlist", recordlist);
            startActivity(intent);
        }
        if(v == btnsettings){
            Intent i = new Intent(this, Settings_Activity.class);
            i.putExtra("color",BackgroundColor);
            activityResultLauncher.launch(i);
        }
        if (v == btnInstruction){
            Intent intent = new Intent(this, Instruction_Activity.class);
            intent.putExtra("color",BackgroundColor);
            startActivity(intent);
        }
        if (v == btnRecord){
            Intent intent = new Intent(this, RecordTable_Activity.class);
            intent.putExtra("color",BackgroundColor);
            startActivity(intent);
        }
        if (v == btnLogout){
            FirebaseAuth.getInstance().signOut();
            finish(); // close the activity
        }
    }
    public void setBackGroundColor(String str) {
        BackgroundColor = str;
        switch (str) {
            case "Blue": {
                linearLayout.setBackgroundColor(Color.BLUE);
                break;
            }
            case "Red": {
                linearLayout.setBackgroundColor(Color.RED);
                break;
            }
            case "Yellow": {
                linearLayout.setBackgroundColor(Color.YELLOW);
                break;
            }
            case "Green": {
                linearLayout.setBackgroundColor(Color.GREEN);
                break;
            }
            case "White": {
                linearLayout.setBackgroundColor(Color.WHITE);
                break;
            }
            case "Pink": {
                linearLayout.setBackgroundColor(Color.argb(255, 255, 182, 193));
                break;
            }
            case "Default":{
                linearLayout.setBackgroundResource(R.drawable.img3);
                break;
            }
            default:
                break;

        }
    }
}