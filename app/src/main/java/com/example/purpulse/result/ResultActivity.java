package com.example.purpulse.result;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.purpulse.NotifyActivity;
import com.example.purpulse.profile.ProfileActivity;
import com.example.purpulse.PulseActivity;
import com.example.purpulse.R;
import com.example.purpulse.RecordActivity;
import com.google.android.material.navigation.NavigationView;

public class ResultActivity extends AppCompatActivity {

    private ImageButton result_img;
    private DrawerLayout result_drawerlayout;
    private NavigationView result_navigation;
    public String activity;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        activity = "result";
        context = this;
        result_img = findViewById(R.id.result_img);
        result_img.setOnClickListener(lis);
        result_drawerlayout = findViewById(R.id.result_drawerlayout);
        result_navigation = findViewById(R.id.result_navigation);
        result_navigation.setNavigationItemSelectedListener(NavigationLis);
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.relativ_result, new ResultFragment(), "result").commit();
        else
            onBackStackChanged();
    }

    View.OnClickListener lis = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.result_img:{
                    result_drawerlayout.openDrawer(Gravity.RIGHT);
                    break;
                }
            }
        }
    };

    NavigationView.OnNavigationItemSelectedListener NavigationLis = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // 點選時收起選單
            result_drawerlayout.closeDrawer(GravityCompat.END);

            int id = item.getItemId();

            // 取得選項id
            if (id == R.id.action_profile) {
                if (activity.equals("profile")){
                    return true;
                }else {
                    startActivity(new Intent(ResultActivity.this, ProfileActivity.class));
                    return true;
                }
            }else if (id == R.id.action_record) {
                if (activity.equals("record")){
                    return true;
                }else {
                    startActivity(new Intent(ResultActivity.this, RecordActivity.class));
                    return true;
                }
            }else if (id == R.id.action_device){
                if (activity.equals("device")){
                    return true;
                }else {
                    startActivity(new Intent(ResultActivity.this,ProfileActivity.class));
                    return true;
                }
            }else if (id == R.id.action_pulse){
                if (activity.equals("pulse")){
                    return true;
                }else {
                    startActivity(new Intent(ResultActivity.this, PulseActivity.class));
                    return true;
                }
            }else {
                if (activity.equals("notify")){
                    return true;
                }else {
                    startActivity(new Intent(ResultActivity.this, NotifyActivity.class));
                    return true;
                }
            }
        }
    };

    private void onBackStackChanged() {
    }

    public boolean onKeyDown(int keycode, KeyEvent event){
        if (keycode == KeyEvent.KEYCODE_BACK){
            if (getSupportFragmentManager().getBackStackEntryCount()>0){
                //getSupportFragmentManager().beginTransaction().replace(R.id.relativ_result, new ResultFragment(), "result").commit();
                getSupportFragmentManager().popBackStack();
            }else{
                finish();
            }
            return true;
        }
        return super.onKeyDown(keycode,event);
    }
}