package com.example.purpulse;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    Button btn_login,btn_gosignup,btn_forgetpw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = findViewById(R.id.btn_login);
        btn_gosignup = findViewById(R.id.btn_gosignup);
        btn_forgetpw = findViewById(R.id.btn_forget);
        btn_login.setOnClickListener(lis);
        btn_gosignup.setOnClickListener(lis);
        btn_forgetpw.setOnClickListener(lis);
    }

    View.OnClickListener lis = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_login:{
                    startActivity(new Intent(LoginActivity.this,HomepageActivity.class));
                    break;
                }
                case R.id.btn_gosignup:{
                    startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
                    break;
                }
                case R.id.btn_forget:{
                    startActivity(new Intent(LoginActivity.this,PasswordActivity.class));
                    break;
                }
            }
        }
    };
}