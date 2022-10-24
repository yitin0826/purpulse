package com.example.purpulse.result;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.purpulse.HomepageActivity;
import com.example.purpulse.R;

public class ResultActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ResultActivity2.this, ResultActivity.class);
        startActivity(i);
        finish();
        super.onBackPressed();
    }
}