package com.example.purpulse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;

public class ProfileActivity extends AppCompatActivity {

    private ImageButton pf_img;
    private Button btn_edit;
    private DrawerLayout pf_drawerlayout;
    private NavigationView pf_navigation;
    public String activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        activity = "profile";
        pf_img = findViewById(R.id.pf_img);
        btn_edit = findViewById(R.id.btn_edit);
        pf_img.setOnClickListener(lis);
        btn_edit.setOnClickListener(lis);
        pf_drawerlayout = findViewById(R.id.pf_drawerlayout);
        pf_navigation = findViewById(R.id.pf_navigation);
        pf_navigation.setNavigationItemSelectedListener(NavigationLis);
    }

    View.OnClickListener lis = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.pf_img:{
                    pf_drawerlayout.openDrawer(Gravity.RIGHT);
                    break;
                }
                case R.id.btn_edit:{
                    startActivity(new Intent(ProfileActivity.this, EditprofileActivity.class));
                    break;
                }
            }

        }
    };

    NavigationView.OnNavigationItemSelectedListener NavigationLis = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // 點選時收起選單
            pf_drawerlayout.closeDrawer(GravityCompat.END);

            int id = item.getItemId();

            // 取得選項id
            if (id == R.id.action_profile) {
                if (activity.equals("profile")){
                    return true;
                }else {
                    startActivity(new Intent(ProfileActivity.this,ProfileActivity.class));
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
                    startActivity(new Intent(ProfileActivity.this,HomepageActivity.class));
                    return true;
                }
            }else if (id == R.id.action_pulse){
                if (activity.equals("pulse")){
                    return true;
                }else {
                    startActivity(new Intent(ProfileActivity.this,PulseActivity.class));
                    return true;
                }
            }else {
                if (activity.equals("notify")){
                    return true;
                }else {
                    //startActivity(new Intent(ProfileActivity.this,ProfileActivity.class));
                    return true;
                }
            }
        }
    };
}