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
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;

public class ProfileActivity extends AppCompatActivity {

    private ImageButton pf_img;
    private DrawerLayout pf_drawerlayout;
    private NavigationView pf_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        pf_img = findViewById(R.id.pf_img);
        pf_img.setOnClickListener(lis);
        pf_drawerlayout = findViewById(R.id.pf_drawerlayout);
        pf_navigation = findViewById(R.id.pf_navigation);
        pf_navigation.setNavigationItemSelectedListener(NavigationLis);
    }

    View.OnClickListener lis = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            pf_drawerlayout.openDrawer(Gravity.RIGHT);
        }
    };

    NavigationView.OnNavigationItemSelectedListener NavigationLis = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // 點選時收起選單
            pf_drawerlayout.closeDrawer(GravityCompat.END);

            // 取得選項id
            int id = item.getItemId();

            if (id == R.id.action_profile) {
                return true;
            }
            else if (id == R.id.action_record) {
                return true;
            }
            return false;
        }
    };
}