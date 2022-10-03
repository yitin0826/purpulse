package com.example.purpulse;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import androidx.fragment.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class ConnectionActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private ConnectionPagerAdapter adapter;
    private CircleIndicator circleIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        initView();
        initFragment();
    }

    private void initFragment(){
        fragmentList.add(new AbdomenFragment());
        fragmentList.add(new LefthandFragment());
        fragmentList.add(new RighthandFragment());
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

    public void showToast(String msg){

        Toast.makeText(ConnectionActivity.this,msg+"",Toast.LENGTH_SHORT).show();

    }

    ViewPager.OnPageChangeListener lis = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position){
                case 0:
                    showToast("fragment1");
                    break;
                case 1:
                    showToast("fragment2");
                    break;
                case 2:
                    showToast("fragment3");
                    break;
                default:
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}