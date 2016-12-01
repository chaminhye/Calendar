package com.example.mh.calendarproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kwanwoo on 2016-09-05.
 */
public class MyAdapter extends BaseAdapter {
    private Context mContext;
    private int mResource;
    private ArrayList<MyItem> mItems = new ArrayList<MyItem>();

    public MyAdapter(Context context, int resource, ArrayList<MyItem> items) {
        mContext = context;
        mItems = items;
        mResource = resource;
    }
    @Override
    public int getCount() {
        return mItems.size();
    }
    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent,false);
        }
        // Set Text 01
        TextView aclass = (TextView) convertView.findViewById(R.id.textItem1);
        aclass.setText(mItems.get(position).nClass);
        // Set Text 02
        TextView day = (TextView) convertView.findViewById(R.id.textItem2);
        day.setText(String.valueOf(mItems.get(position).nDay));

        return convertView;
    }
}

class MyItem {
    String nClass; // image resource
    int nDay; // text


    MyItem(String aclass, int aName) {
        nClass= aclass;
        nDay = aName;

    }
}
