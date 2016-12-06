package com.example.mh.calendarproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sea on 2016-10-12.
 */
public class Monthly extends Fragment implements View.OnClickListener,AdapterView.OnItemClickListener{
    ArrayList<String> mItems;
    ArrayAdapter<String> adapter;
    TextView textYear;
    TextView textMon;
    private Calendar mCal;
    public Monthly(){}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.monthly,container,false);

        ActionBar actionBar=((MainActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Monthly Schedule");

        textYear = (TextView)view.findViewById(R.id.edit1);
        textMon = (TextView) view.findViewById(R.id.edit2);
        mItems = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(inflater.getContext(),
                android.R.layout.simple_list_item_1, mItems);

        GridView gird = (GridView)view.findViewById(R.id.grid1);
        gird.setAdapter(adapter);
        gird.setOnItemClickListener(this);

        Date date = new Date();
        int year = date.getYear() + 1900;
        int mon = date.getMonth() + 1;
        textYear.setText(year + "");
        textMon.setText(mon + "");

        fillDate(year, mon);

        Button btnmove = (Button) view.findViewById(R.id.bt1);
        btnmove.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        if (arg0.getId() == R.id.bt1) {
            int year = Integer.parseInt(textYear.getText().toString());
            int mon = Integer.parseInt(textMon.getText().toString());
            fillDate(year, mon);
        }
    }
    private void fillDate(int year, int mon) {
        mItems.clear();

        mItems.add("일");
        mItems.add("월");
        mItems.add("화");
        mItems.add("수");
        mItems.add("목");
        mItems.add("금");
        mItems.add("토");

        Date current = new Date(year - 1900, mon - 1,1);
        int day = current.getDay(); // ���ϵ� int�� ����.


        for (int i = 0; i < day; i++) {
            mItems.add("");
        }

        current.setDate(32);// 32�ϱ��� �Է��ϸ� 1�Ϸ� �ٲ��ش�.
        int last = 32 - current.getDate();

        for (int i = 1; i <= last; i++) {
            mItems.add(i + "");
        }
        //해당날짜 텍스트 컬러,배경 변경
        mCal= Calendar.getInstance();
        //오늘 DAY가져옴
        Integer today = mCal.get(Calendar.DAY_OF_MONTH);
        String sToday=String.valueOf(today);
        if(sToday.equals(today)){

        }
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub
        if (mItems.get(arg2).equals("")) {
            ;
        } else {
            Intent intent = new Intent(getActivity(), Schedule_list.class);
            intent.putExtra("Param1", textYear.getText().toString() + "/"
                    + textMon.getText().toString() + "/" + mItems.get(arg2));

            Log.i("Param1",intent.getStringExtra("Param1"));
            startActivity(intent);
        }
    }
}
