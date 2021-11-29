package com.example.remindapp_phamvanthien;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Switch;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<event> eventArrayList;
    eventAdapter eventAdapter;
    FloatingActionButton btnAdd;
    int REQUEST_EVENT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        addEvent();
        eventArrayList = new ArrayList<>();
        eventAdapter = new eventAdapter(eventArrayList,this);
        listView.setAdapter(eventAdapter);
    }

    private void mapping() {
        listView = (ListView) findViewById(R.id.listViewa);
        btnAdd = (FloatingActionButton) findViewById(R.id.btnAdd);
    }
    public void addEvent(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DetailEventActivity.class);
                startActivityForResult(intent,REQUEST_EVENT);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(REQUEST_EVENT == requestCode && resultCode == RESULT_OK & data != null){
            event event = (event) data.getSerializableExtra("event");
            eventArrayList.add(event);
            eventAdapter.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void delete(final int position){
        eventArrayList.remove(position);
        eventAdapter.notifyDataSetChanged();
    }

}