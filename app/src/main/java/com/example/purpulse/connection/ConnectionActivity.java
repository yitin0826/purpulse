package com.example.purpulse.connection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import com.example.purpulse.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class ConnectionActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private ConnectionPagerAdapter adapter;
    private CircleIndicator circleIndicator;
    private String device;
    Fragment fragment = new RighthandFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        Intent intent = this.getIntent();
        device = intent.getStringExtra("device");
        //Toast.makeText(ConnectionActivity.this,device,Toast.LENGTH_SHORT).show();
        Bundle args = new Bundle();
        args.putString("device",device);
        fragment.setArguments(args);
        initView();
        initFragment();
    }

    private void initFragment(){
        fragmentList.add(new AbdomenFragment());
        fragmentList.add(new LefthandFragment());
        fragmentList.add(fragment);
        adapter = new ConnectionPagerAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        circleIndicator.setViewPager(viewPager);
    }

    public void initView(){
        viewPager = findViewById(R.id.viewPager_connection);
        viewPager.setOnPageChangeListener(lis);
        circleIndicator = findViewById(R.id.indicator);
    }

    ViewPager.OnPageChangeListener lis = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}