package com.example.purpulse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CodeActivity extends AppCompatActivity {

    Button code_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        code_confirm = findViewById(R.id.code_confirm);
        code_confirm.setOnClickListener(lis);
    }

    View.OnClickListener lis = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.code_confirm:{
                    startActivity(new Intent(CodeActivity.this,NewpasswordActivity.class));
                    break;
                }
            }
        }
    };
}