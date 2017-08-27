package com.example.summerproject.summerproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mohamed Abd Elaziz on 8/26/2017.
 */

public class Answer_list_adapter extends BaseAdapter {
    Context context;
    ArrayList<String>arrayList;

    public Answer_list_adapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView=layoutInflater.inflate(R.layout.grid_answer_item,null);
        TextView textView = (TextView) convertView.findViewById(R.id.textv);
        textView.setText(arrayList.get(position));
        return convertView;
    }
}
