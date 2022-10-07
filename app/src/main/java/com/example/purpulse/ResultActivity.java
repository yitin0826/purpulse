package com.example.purpulse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private TextView txt_heartrate;
    private ViewPager vp_result;
    private TabLayout tl_result;
    private List<Fragment> fragmentList = new ArrayList<>();
    public static int lastPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initView();
        setText();
    }

    public void setText(){
        txt_heartrate = findViewById(R.id.txt_heartrate);
        txt_heartrate.setText("180bpm");
        SpannableStringBuilder span = new SpannableStringBuilder(txt_heartrate.getText().toString());
        span.setSpan(new AbsoluteSizeSpan(sp2px(this,70)),
                0,3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new AbsoluteSizeSpan(sp2px(this,10)),
                3,6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        txt_heartrate.setText(span);
    }

    private int sp2px(Context context, float spValue){
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale +0.5f);
    }

    public void initView(){
        vp_result = findViewById(R.id.vp_result);
        vp_result.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                lastPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(vp_result);
        tl_result = findViewById(R.id.tl_result);
        tl_result.setupWithViewPager(vp_result);
        tl_result.getTabAt(0).setIcon(R.drawable.scatter_graph);
        tl_result.getTabAt(1).setIcon(R.drawable.yin_yang);
    }

    private void setupViewPager(ViewPager viewPager){
        fragmentList.add(new ScatterFragment());
        fragmentList.add(new TaijiFragment());
        ResultPagerAdapter adapter = new ResultPagerAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);
    }
}