package com.example.purpulse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.purpulse.connection.ConnectionActivity;
import com.example.purpulse.profile.ProfileActivity;
import com.google.android.material.navigation.NavigationView;

public class HomepageActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener{

    private ImageButton hp_img;
    private Toolbar hp_toolbar;
    private DrawerLayout hp_drawerlayout;
    private NavigationView hp_navigation;
    public View view;
    public String activity;
    private String account;

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
        //收帳號
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        account = bundle.getString("account");
        Log.d("acc",""+account);
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
                    //傳帳號過去當識別資料
                    Intent intent = new Intent();
                    intent.setClass(HomepageActivity.this,ProfileActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("account",""+account);
                    intent.putExtras(bundle);
                    startActivity(intent);
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
                    startActivity(new Intent(HomepageActivity.this, ConnectionActivity.class));
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

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("提示訊息")
                .setMessage("確定要離開這個頁面嗎？")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, (arg0, arg1) -> {
                    super.onBackPressed();
                }).create().show();
    }
}