package com.example.purpulse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.purpulse.profile.ProfileActivity;
import com.example.purpulse.result.ResultPagerAdapter;
import com.example.purpulse.result.ScatterFragment;
import com.example.purpulse.result.TaijiFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecordActivity extends AppCompatActivity {

    RecyclerView  recyclerView;
    MyListAdapter myListAdapter;
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    ArrayList<String> Heart = new ArrayList<>();
    ArrayList<String> Date = new ArrayList<>();
    ArrayList<String> state = new ArrayList<>();
    ImageButton record_img;
    private DrawerLayout record_drawerlayout;
    private NavigationView record_navigation;
    private String activity;
    private String Account = Note.account;
    private static final String DataBaseName = "db";
    private static final int DataBaseVersion = 9;
    private static String DataBaseTable = "Data";
    private static SQLiteDatabase DB;
    private SqlDataBaseHelper sqlDataBaseHelper;

    /** itemClick **/
    private PopupWindow record;
    private TextView txt_lf,txt_sdnn,txt_hf;
    private Button record_ok;
    public static int lastPosition = 0;
    private ViewPager vp_record;
    private TabLayout tl_record;
    private ResultPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        activity = "record";
        record_img = findViewById(R.id.record_img);
        record_drawerlayout = findViewById(R.id.record_drawerlayout);
        record_navigation = findViewById(R.id.record_navigation);
        record_navigation.setNavigationItemSelectedListener(NavigationLis);
        record_img.setOnClickListener(lis);
        initRecycle();
    }

    NavigationView.OnNavigationItemSelectedListener NavigationLis = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // 點選時收起選單
            record_drawerlayout.closeDrawer(GravityCompat.END);

            int id = item.getItemId();

            // 取得選項id
            if (id == R.id.action_profile) {
                if (activity.equals("profile")){
                    return true;
                }else {
                    startActivity(new Intent(RecordActivity.this, ProfileActivity.class));
                    return true;
                }
            }else if (id == R.id.action_record) {
                if (activity.equals("record")){
                    return true;
                }else {
                    startActivity(new Intent(RecordActivity.this,RecordActivity.class));
                    return true;
                }
            }else if (id == R.id.action_device){
                if (activity.equals("device")){
                    return true;
                }else {
                    startActivity(new Intent(RecordActivity.this,HomepageActivity.class));
                    return true;
                }
            }else if (id == R.id.action_pulse){
                if (activity.equals("pulse")){
                    return true;
                }else {
                    startActivity(new Intent(RecordActivity.this,PulseActivity.class));
                    return true;
                }
            }else {
                if (activity.equals("notify")){
                    return true;
                }else {
                    startActivity(new Intent(RecordActivity.this,NotifyActivity.class));
                    return true;
                }
            }
        }
    };

    View.OnClickListener lis = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.record_img:{
                    record_drawerlayout.openDrawer(Gravity.RIGHT);
                    break;
                }
                case R.id.record_ok:{
                    record.dismiss();
                    break;
                }
            }
        }
    };

    public void initRecycle(){
        // 建立SQLiteOpenHelper物件
        sqlDataBaseHelper = new SqlDataBaseHelper(RecordActivity.this,DataBaseName,null,DataBaseVersion,DataBaseTable);
        DB = sqlDataBaseHelper.getWritableDatabase(); // 開啟資料庫
        Cursor D = DB.rawQuery("SELECT * FROM Data",null);
        D.moveToFirst();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        myListAdapter = new MyListAdapter();
        recyclerView.setAdapter(myListAdapter);
        HashMap<String,String> hashMap = new HashMap<>();
        for (int i = 0;i<D.getCount();i++){     //按照順序顯示資料
            Heart.add(D.getString(8));
            Date.add(D.getString(1));
            state.add(D.getString(2));
            D.moveToNext();     //下一筆資料
        }
    }

    private class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{

        class ViewHolder extends RecyclerView.ViewHolder{
            private TextView item_heartrate,item_status,item_date;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                item_heartrate = itemView.findViewById(R.id.item_heartrate);
                item_status = itemView.findViewById(R.id.item_status);
                item_date = itemView.findViewById(R.id.item_date);
                popRecord();

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Note.Date = item_date.getText().toString();
                        Log.d("date",""+Note.Date);

                        // 建立SQLiteOpenHelper物件
                        sqlDataBaseHelper = new SqlDataBaseHelper(RecordActivity.this,DataBaseName,null,DataBaseVersion,DataBaseTable);
                        DB = sqlDataBaseHelper.getWritableDatabase(); // 開啟資料庫
                        Cursor D = DB.rawQuery("SELECT * FROM Data WHERE time LIKE '"+Note.Date+"'",null);
                        D.moveToFirst();
                        //點擊歷史紀錄顯示的值
                        txt_lf.setText(D.getString(6));
                        txt_hf.setText(D.getString(7));
                        txt_sdnn.setText(D.getString(5));
                        //太極圖數據
                        Note.HFn = D.getDouble(7);
                        Note.LFn = D.getDouble(6);
                        Note.sdNN = D.getDouble(4);
//                        Note.RRi = D.getString(9);

                        record.showAtLocation(view, Gravity.CENTER_HORIZONTAL, 0, 0);
                    }
                });
            }
        }

        @NonNull
        @Override
        public MyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyListAdapter.ViewHolder holder, int position) {
            holder.item_heartrate.setText(Heart.get(position));
            holder.item_status.setText(state.get(position));
            holder.item_date.setText(Date.get(position));
        }

        @Override
        public int getItemCount() {
            return Date.size();
        }

        public void popRecord(){
            View view = LayoutInflater.from(RecordActivity.this).inflate(R.layout.popwindow_record,null);
            record = new PopupWindow(view);
            int width = getWindowManager().getDefaultDisplay().getWidth();
            record.setWidth(width*5/7);
            record.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            record.setFocusable(false);
            txt_lf = view.findViewById(R.id.txt_lf);
            txt_sdnn = view.findViewById(R.id.txt_sdnn);
            txt_hf = view.findViewById(R.id.txt_hf);
            record_ok = view.findViewById(R.id.record_ok);
            vp_record = view.findViewById(R.id.vp_record);
            tl_record = view.findViewById(R.id.tl_record);
            record_ok.setOnClickListener(lis);
            //initView();
        }

        public void initView(){
            vp_record.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
            setupViewPager(vp_record);
            tl_record.setupWithViewPager(vp_record);
            tl_record.getTabAt(0).setIcon(R.drawable.scatter_graph);
            tl_record.getTabAt(1).setIcon(R.drawable.yin_yang);
        }

        private void setupViewPager(ViewPager viewPager){
            List<Fragment> fragmentList = new ArrayList<>();
            fragmentList.add(new ScatterFragment());
            fragmentList.add(new TaijiFragment());
            adapter = new ResultPagerAdapter(getSupportFragmentManager(),RecordActivity.this,fragmentList);
            viewPager.setAdapter(adapter);
        }
    }
}