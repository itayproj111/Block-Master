package com.example.proj;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FbModule {
    private Context context;
    private MainActivity mainActivity;
    private FirebaseDatabase database;
    private ArrayList<Record> recordlist;



    public FbModule(MainActivity mainActivity, ArrayList<Record> recordlist) {


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference("color"+ FirebaseAuth.getInstance().getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String str = snapshot.getValue(String.class);
                if (str != null){
                    mainActivity.setBackGroundColor(str);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    public void setRecord(String name, int record)
    {
        // Write a message to the database
        DatabaseReference myRef = database.getReference("records/").push(); // push adds new node with unique value

        //DatabaseReference myRef = database.getReference("records/" + FirebaseAuth.getInstance().getUid());

        Record rec = new Record(name, record);
        myRef.setValue(rec);
    }

    public void setPrivateRecord(String name, int record)
    {
        // Write a message to the database
        //DatabaseReference myRef = database.getReference("records").push(); // push adds new node with unique value

        DatabaseReference myRef = database.getReference("records/" + FirebaseAuth.getInstance().getUid());
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        currentUser.getEmail();
        Record rec = new Record(name, record);
        myRef.setValue(rec);
    }
    public void changeBackgroundColorInFireBase(String str){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference("color"+ FirebaseAuth.getInstance().getUid());
        reference.setValue(str);
    }
}
