package com.example.purpulse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class HomepageActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener{

    private ImageButton hp_img;
    private Toolbar hp_toolbar;
    private DrawerLayout hp_drawerlayout;
    private NavigationView hp_navigation;
    public View view;
    public String activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        activity = "device";
        hp_img = findViewById(R.id.hp_img);
        hp_img.setOnClickListener(lis);
        hp_drawerlayout = findViewById(R.id.hp_drawerlayout);
        hp_navigation = findViewById(R.id.hp_navigation);
        hp_navigation.setNavigationItemSelectedListener(NavigationLis);
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.device, new DeviceFragment(), "devices").commit();
        else
            onBackStackChanged();
    }

    View.OnClickListener lis = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            hp_drawerlayout.openDrawer(Gravity.RIGHT);
        }
    };

    NavigationView.OnNavigationItemSelectedListener NavigationLis = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // 點選時收起選單
            hp_drawerlayout.closeDrawer(GravityCompat.END);

            // 取得選項id
            int id = item.getItemId();

            // 依照id判斷點了哪個項目並做相應事件
            if (id == R.id.action_profile) {
                if (activity.equals("profile")){
                    return true;
                }else {
                    startActivity(new Intent(HomepageActivity.this,ProfileActivity.class));
                    return true;
                }
            }else if (id == R.id.action_record) {
                if (activity.equals("record")){
                    return true;
                }else {
                    startActivity(new Intent(HomepageActivity.this,RecordActivity.class));
                    return true;
                }
            }else if (id == R.id.action_device){
                if (activity.equals("device")){
                    return true;
                }else {
                    startActivity(new Intent(HomepageActivity.this,ProfileActivity.class));
                    return true;
                }
            }else if (id == R.id.action_pulse){
                if (activity.equals("pulse")){
                    return true;
                }else {
                    startActivity(new Intent(HomepageActivity.this,PulseActivity.class));
                    return true;
                }
            }else {
                if (activity.equals("notify")){
                    return true;
                }else {
                    startActivity(new Intent(HomepageActivity.this,NotifyActivity.class));
                    return true;
                }
            }
        }
    };

    @Override
    public void onBackStackChanged() {
        //getSupportActionBar().setDisplayHomeAsUpEnabled(getSupportFragmentManager().getBackStackEntryCount()>0);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}