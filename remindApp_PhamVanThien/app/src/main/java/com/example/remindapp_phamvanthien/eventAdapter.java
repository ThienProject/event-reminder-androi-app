package com.example.remindapp_phamvanthien;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class eventAdapter extends BaseAdapter {
    ArrayList<event> eventArray;
  //  Context activity;
    private MainActivity activity;

    public eventAdapter(ArrayList<event> eventArray, MainActivity activity) {
        this.eventArray = eventArray;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return eventArray.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }
    private class ViewHolder{
        TextView txtTitle;
        TextView txtDes, txtDate ;
        ImageButton imgBtnDelete;
        LinearLayout linearLayout;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater =((Activity)activity).getLayoutInflater();
            convertView = inflater.inflate(R.layout.event_item,null);
            holder.linearLayout = (LinearLayout)convertView.findViewById(R.id.alarm_item);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
            holder.txtDes = (TextView)  convertView.findViewById(R.id.txtDes);
            holder.txtDate = (TextView)  convertView.findViewById(R.id.txtDate);
            holder.imgBtnDelete = (ImageButton) convertView.findViewById(R.id.imgBtnDelete);

            // Lấy sự kiện khi click vào nút xóa
            holder.imgBtnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.delete(position);
                }
            });
            // Lấy sự kiện khi click vào một item của listView
            holder.linearLayout.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity,DetailEventActivity.class);
                    activity.startActivity(intent);
                }
            });
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        event event = eventArray.get(position);
        holder.txtTitle.setText(event.getTitle());
        holder.txtDes.setText(event.getDescrition());
        holder.txtDate.setText(event.getDate());
        return convertView;


    }
}
