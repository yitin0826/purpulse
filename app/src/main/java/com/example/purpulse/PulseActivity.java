package com.example.purpulse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class PulseActivity extends AppCompatActivity {

    private ImageButton pulse_img;
    private DrawerLayout pulse_drawerlayout;
    private NavigationView pulse_navigation;
    private ProgressBar progressBar;
    private TextView progressText;
    int i = 0;
    private Button btn_resultconfirm;
    public String activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulse);
        activity = "pulse";
        btn_resultconfirm = findViewById(R.id.btn_resultconfirm);
        btn_resultconfirm.setOnClickListener(lis);
        btn_resultconfirm.setPaintFlags(btn_resultconfirm.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        pulse_img = findViewById(R.id.pulse_img);
        pulse_img.setOnClickListener(lis);
        pulse_drawerlayout = findViewById(R.id.pulse_drawerlayout);
        pulse_navigation = findViewById(R.id.pulse_navigation);
        pulse_navigation.setNavigationItemSelectedListener(NavigationLis);
        //initProgressBar();
    }

    View.OnClickListener lis = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_resultconfirm:{
                    startActivity(new Intent(PulseActivity.this,ResultActivity.class));
                    break;
                }
                case R.id.pulse_img:{
                    pulse_drawerlayout.openDrawer(Gravity.RIGHT);
                    break;
                }
            }
        }
    };

//    public void initProgressBar(){
//        progressBar = findViewById(R.id.progressbar);
//        progressText = findViewById(R.id.progress_text);
//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (i<=100){
//                    progressText.setText(""+i+"%");
//                    progressBar.setProgress(i);
//                    i+=1;
//                    handler.postDelayed(this,900);
//                }
//                else {
//                    handler.removeCallbacks(this);
//                }
//            }
//        },200);
//    }

    NavigationView.OnNavigationItemSelectedListener NavigationLis = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // 點選時收起選單
            pulse_drawerlayout.closeDrawer(GravityCompat.END);

            int id = item.getItemId();

            // 取得選項id
            if (id == R.id.action_profile) {
                if (activity.equals("profile")){
                    return true;
                }else {
                    startActivity(new Intent(PulseActivity.this,ProfileActivity.class));
                    return true;
                }
            }else if (id == R.id.action_record) {
                if (activity.equals("record")){
                    return true;
                }else {
                    //startActivity(new Intent(HomepageActivity.this,ProfileActivity.class));
                    return true;
                }
            }else if (id == R.id.action_device){
                if (activity.equals("device")){
                    return true;
                }else {
                    startActivity(new Intent(PulseActivity.this,ProfileActivity.class));
                    return true;
                }
            }else if (id == R.id.action_pulse){
                if (activity.equals("pulse")){
                    return true;
                }else {
                    startActivity(new Intent(PulseActivity.this,PulseActivity.class));
                    return true;
                }
            }else {
                if (activity.equals("notify")){
                    return true;
                }else {
                    startActivity(new Intent(PulseActivity.this,ProfileActivity.class));
                    return true;
                }
            }
        }
    };
}