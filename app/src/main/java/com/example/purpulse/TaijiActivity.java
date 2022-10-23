package com.example.purpulse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class TaijiActivity extends AppCompatActivity {

    ArrayList<NameMapping> taiji = new ArrayList<NameMapping>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taiji);
        ListItem();
    }

    public void ListItem(){
        taiji.add(new NameMapping("平常有鍛鍊，生活作息正常，睡眠正常。",R.mipmap.yinyang_1));
        taiji.add(new NameMapping("長壽體質，胃腸與免疫功能較強，易入睡。",R.mipmap.yinyang_2));

        TaijiAdapter adapter = new TaijiAdapter(this, taiji);
        ListView listView = findViewById(R.id.list_taiji);
        listView.setAdapter(adapter);
    }
}