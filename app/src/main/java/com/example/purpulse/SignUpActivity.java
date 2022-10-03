package com.example.purpulse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpActivity extends AppCompatActivity {

    Button btn_signup,btn_gologin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btn_signup = findViewById(R.id.btn_signup);
        btn_gologin = findViewById(R.id.btn_gologin);
        btn_signup.setOnClickListener(lis);
        btn_gologin.setOnClickListener(lis);
    }

    View.OnClickListener lis = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_signup:{

                }
                case R.id.btn_gologin:{
                    startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                }
            }
        }
    };
}