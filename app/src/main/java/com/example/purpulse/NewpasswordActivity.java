package com.example.purpulse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewpasswordActivity extends AppCompatActivity {

    private Button npw_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpassword);
        npw_confirm = findViewById(R.id.npw_confirm);
        npw_confirm.setOnClickListener(lis);
    }

    View.OnClickListener lis = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(NewpasswordActivity.this,LoginActivity.class));
        }
    };
}