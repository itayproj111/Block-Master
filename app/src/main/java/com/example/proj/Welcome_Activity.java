package com.example.proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Welcome_Activity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    Button btnRegister, btnLogin;
    EditText etEmailAddress, etNumberPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        etEmailAddress = findViewById(R.id.etEmailAddress);
        etNumberPassword = findViewById(R.id.etNumberPassword);

        mAuth = FirebaseAuth.getInstance();
    }
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }
    private void reload() {
        Intent intent = new Intent(Welcome_Activity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if(v == btnRegister)
        {
            String email = etEmailAddress.getText().toString();
            String password = etNumberPassword.getText().toString();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Intent intent = new Intent(Welcome_Activity.this, MainActivity.class);
                                startActivity(intent);

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(Welcome_Activity.this, "fail register", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

        if(v == btnLogin)
        {
            String email = etEmailAddress.getText().toString();
            String password = etNumberPassword.getText().toString();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Intent intent = new Intent(Welcome_Activity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(Welcome_Activity.this, "login failed.", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
    }
}