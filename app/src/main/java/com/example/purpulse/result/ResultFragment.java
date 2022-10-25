package com.example.purpulse.result;

import android.content.Context;
import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.purpulse.BackHandlerHelper;
import com.example.purpulse.FragmentBackHandler;
import com.example.purpulse.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ResultFragment extends Fragment{

    private TextView txt_heartrate,text_g,text_y,text_r,text_p;
    private ViewPager vp_result;
    private TabLayout tl_result;
    private List<Fragment> fragmentList = new ArrayList<>();
    public static int lastPosition = 0;
    private ImageButton result_img,btn_info;
    private Button btn_ok,btn_next;
    private DrawerLayout result_drawerlayout;
    private NavigationView result_navigation;
    public String activity;
    private PopupWindow info;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        btn_info = view.findViewById(R.id.btn_info);
        btn_next = view.findViewById(R.id.btn_next);
        btn_info.setOnClickListener(lis);
        btn_next.setOnClickListener(lis);
        txt_heartrate = view.findViewById(R.id.txt_heartrate);
        vp_result = view.findViewById(R.id.vp_result);
        tl_result = view.findViewById(R.id.tl_result);
        initView();
        infoWindow();
        setText();
        return view;
    }

    public void infoWindow(){
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.popwindow_info,null);
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
                case R.id.btn_info:{
                    info.showAtLocation(view, Gravity.CENTER_HORIZONTAL, 0, 0);
                    break;
                }
                case R.id.btn_ok:{
                    info.dismiss();
                    break;
                }
                case R.id.btn_next:{
                    //切換頁面
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.relativ_result,new ResultFragment2());
                    fragmentTransaction.commit();
                    break;
                }
            }
        }
    };

    public void setText(){
        txt_heartrate.setText("180bpm");
        SpannableStringBuilder span = new SpannableStringBuilder(txt_heartrate.getText().toString());
        span.setSpan(new AbsoluteSizeSpan(sp2px(getActivity(),70)),
                0,3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new AbsoluteSizeSpan(sp2px(getActivity(),10)),
                3,6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        txt_heartrate.setText(span);
    }

    private int sp2px(Context context, float spValue){
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale +0.5f);
    }

    public void initView(){
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
        tl_result.setupWithViewPager(vp_result);
        tl_result.getTabAt(0).setIcon(R.drawable.scatter_graph);
        tl_result.getTabAt(1).setIcon(R.drawable.yin_yang);
    }

    private void setupViewPager(ViewPager viewPager){
        fragmentList.add(new ScatterFragment());
        fragmentList.add(new TaijiFragment());
        ResultPagerAdapter adapter = new ResultPagerAdapter(getActivity().getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);
    }
}