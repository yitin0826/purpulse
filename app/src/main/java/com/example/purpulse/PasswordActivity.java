package com.example.purpulse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PasswordActivity extends AppCompatActivity {

    Button pw_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        pw_confirm = findViewById(R.id.pw_confirm);
        pw_confirm.setOnClickListener(lis);
    }

    View.OnClickListener lis = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.pw_confirm:{
                    startActivity(new Intent(PasswordActivity.this,NewpasswordActivity.class));
                    break;
                }
            }
        }
    };
}