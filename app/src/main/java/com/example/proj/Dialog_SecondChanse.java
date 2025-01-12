package com.example.proj;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

public class Dialog_SecondChanse extends Dialog implements View.OnClickListener{
    private Button btnyes, btnno;
    private Context context;

    public Dialog_SecondChanse(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_second_chanse);
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
            ((Game_Activity)context).continue1();
        }
        if(btnno==v){
            Dialog_GameOver customDialog = new Dialog_GameOver(context);
            customDialog.show();

        }
    }
}
