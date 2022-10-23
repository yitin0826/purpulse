package com.example.purpulse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;

public class RecordActivity extends AppCompatActivity {

    RecyclerView  recyclerView;
    MyListAdapter myListAdapter;
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    ImageButton record_img;
    private DrawerLayout record_drawerlayout;
    private NavigationView record_navigation;
    private String activity;

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
                    startActivity(new Intent(RecordActivity.this,ProfileActivity.class));
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
                    startActivity(new Intent(RecordActivity.this,ProfileActivity.class));
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
            }
        }
    };

    public void initRecycle(){
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        myListAdapter = new MyListAdapter();
        recyclerView.setAdapter(myListAdapter);
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("heart","79");
        hashMap.put("date","2022-10-18 14:57");
        hashMap.put("status","睡前量測");
        arrayList.add(hashMap);
    }

    private class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{

        class ViewHolder extends RecyclerView.ViewHolder{
            private TextView item_heartrate,item_status,item_date;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                item_heartrate = itemView.findViewById(R.id.item_heartrate);
                item_status = itemView.findViewById(R.id.item_status);
                item_date = itemView.findViewById(R.id.item_date);
            }
        }

        @NonNull
        @Override
        public MyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_item,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyListAdapter.ViewHolder holder, int position) {
            holder.item_heartrate.setText(arrayList.get(position).get("heart"));
            holder.item_status.setText(arrayList.get(position).get("status"));
            holder.item_date.setText(arrayList.get(position).get("date"));
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }
    }
}