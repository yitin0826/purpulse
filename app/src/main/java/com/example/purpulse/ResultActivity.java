package com.example.purpulse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private TextView txt_heartrate,text_g,text_y,text_r,text_p;
    private ViewPager vp_result;
    private TabLayout tl_result;
    private List<Fragment> fragmentList = new ArrayList<>();
    public static int lastPosition = 0;
    private ImageButton result_img,btn_info;
    private Button btn_ok;
    private DrawerLayout result_drawerlayout;
    private NavigationView result_navigation;
    public String activity;
    private PopupWindow info;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        activity = "result";
        context = this;
        result_img = findViewById(R.id.result_img);
        btn_info = findViewById(R.id.btn_info);
        result_img.setOnClickListener(lis);
        btn_info.setOnClickListener(lis);
        result_drawerlayout = findViewById(R.id.result_drawerlayout);
        result_navigation = findViewById(R.id.result_navigation);
        result_navigation.setNavigationItemSelectedListener(NavigationLis);
        initView();
        setText();
        infoWindow();
    }

    public void infoWindow(){
        View view = LayoutInflater.from(context).inflate(R.layout.infowindow,null);
        info = new PopupWindow(view);
        info.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        info.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        info.setFocusable(false);
        text_g = view.findViewById(R.id.text_g);
        text_y = view.findViewById(R.id.text_y);
        text_r = view.findViewById(R.id.text_r);
        text_p = view.findViewById(R.id.text_p);
        btn_ok = view.findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(lis);
        ForegroundColorSpan greenSpan = new ForegroundColorSpan(getResources().getColor(R.color.info_g));
        ForegroundColorSpan yellowSpan = new ForegroundColorSpan(getResources().getColor(R.color.info_y));
        ForegroundColorSpan redSpan = new ForegroundColorSpan(getResources().getColor(R.color.info_r));
        ForegroundColorSpan purpleSpan = new ForegroundColorSpan(getResources().getColor(R.color.info_p));
        SpannableStringBuilder builder_g = new SpannableStringBuilder(text_g.getText().toString());
        SpannableStringBuilder builder_y = new SpannableStringBuilder(text_y.getText().toString());
        SpannableStringBuilder builder_r = new SpannableStringBuilder(text_r.getText().toString());
        SpannableStringBuilder builder_p = new SpannableStringBuilder(text_p.getText().toString());
        builder_g.setSpan(greenSpan,3,5, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        builder_y.setSpan(yellowSpan,3,5, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        builder_r.setSpan(redSpan,3,5, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        builder_p.setSpan(purpleSpan,3,5, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        text_g.setText(builder_g);
        text_y.setText(builder_y);
        text_r.setText(builder_r);
        text_p.setText(builder_p);
    }

    View.OnClickListener lis = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.result_img:{
                    result_drawerlayout.openDrawer(Gravity.RIGHT);
                    break;
                }
                case R.id.btn_info:{
                    info.showAtLocation(view, Gravity.CENTER_HORIZONTAL, 0, 0);
                    break;
                }
                case R.id.btn_ok:{
                    info.dismiss();
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
                    startActivity(new Intent(ResultActivity.this,ProfileActivity.class));
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
                    startActivity(new Intent(ResultActivity.this,ProfileActivity.class));
                    return true;
                }
            }else if (id == R.id.action_pulse){
                if (activity.equals("pulse")){
                    return true;
                }else {
                    startActivity(new Intent(ResultActivity.this,PulseActivity.class));
                    return true;
                }
            }else {
                if (activity.equals("notify")){
                    return true;
                }else {
                    //startActivity(new Intent(ResultActivity.this,ProfileActivity.class));
                    return true;
                }
            }
        }
    };

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