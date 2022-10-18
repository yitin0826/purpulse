package com.example.purpulse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class RecordActivity extends AppCompatActivity {

    RecyclerView  recyclerView;
    MyListAdapter myListAdapter;
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        initRecycle();
    }

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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item,parent,false);
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