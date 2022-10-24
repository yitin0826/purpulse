package com.example.purpulse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.purpulse.profile.ProfileActivity;
import com.google.android.material.navigation.NavigationView;

public class PulseActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener{

    private Fragment fragment = new PulseFragment();
    private ImageButton pulse_img;
    private DrawerLayout pulse_drawerlayout;
    private NavigationView pulse_navigation;
    private ProgressBar progressBar;
    private TextView progressText;
    int i = 0;
    private Button btn_resultconfirm;
    public String activity,device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulse);
        activity = "pulse";
        Intent intent = this.getIntent();
        device = intent.getStringExtra("device");
        Bundle args = new Bundle();
        args.putString("device",device);
        fragment.setArguments(args);
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.pulse, fragment, "pulse").commit();
        else
            onBackStackChanged();
        pulse_img = findViewById(R.id.pulse_img);
        pulse_img.setOnClickListener(lis);
        pulse_drawerlayout = findViewById(R.id.pulse_drawerlayout);
        pulse_navigation = findViewById(R.id.pulse_navigation);
        pulse_navigation.setNavigationItemSelectedListener(NavigationLis);
    }

    View.OnClickListener lis = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.pulse_img:{
                    pulse_drawerlayout.openDrawer(Gravity.RIGHT);
                    break;
                }
            }
        }
    };

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
                    startActivity(new Intent(PulseActivity.this, ProfileActivity.class));
                    return true;
                }
            }else if (id == R.id.action_record) {
                if (activity.equals("record")){
                    return true;
                }else {
                    startActivity(new Intent(PulseActivity.this,RecordActivity.class));
                    return true;
                }
            }else if (id == R.id.action_device){
                if (activity.equals("device")){
                    return true;
                }else {
                    startActivity(new Intent(PulseActivity.this,HomepageActivity.class));
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
                    startActivity(new Intent(PulseActivity.this,NotifyActivity.class));
                    return true;
                }
            }
        }
    };

    public void onBackStackChanged() {
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("提示訊息")
                .setMessage("若返回則當前量測資料會被清除，確定要結束量測嗎？")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, (arg0, arg1) -> {
                    super.onBackPressed();
                }).create().show();
    }
}