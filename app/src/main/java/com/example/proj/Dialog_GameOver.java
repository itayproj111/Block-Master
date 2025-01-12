package com.example.proj;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

public class Dialog_GameOver extends Dialog implements View.OnClickListener {
    private Button btnyes, btnno;
    private Context context;

    public Dialog_GameOver(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_game_over);
        btnno = findViewById(R.id.btnno);
        btnyes = findViewById(R.id.btnyes);
        btnno.setOnClickListener(this);
        btnyes.setOnClickListener(this);
        this.context=context;
    }
    @Override
    public void onClick(View v) {
        if(btnyes==v){
            dismiss();
            ((Game_Activity)context).reset();
        }
        if(btnno==v){
            ((Game_Activity)context).finish();
        }
    }

}
