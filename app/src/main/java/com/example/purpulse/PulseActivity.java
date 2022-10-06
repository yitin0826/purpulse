package com.example.purpulse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PulseActivity extends AppCompatActivity {

    Button btn_resultconfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulse);
        btn_resultconfirm = findViewById(R.id.btn_resultconfirm);
        btn_resultconfirm.setOnClickListener(lis);
        btn_resultconfirm.setPaintFlags(btn_resultconfirm.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    View.OnClickListener lis = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(PulseActivity.this,ResultActivity.class));
        }
    };
}