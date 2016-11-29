package com.example.mh.calendarproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Sea on 2016-10-12.
 */
public class Weekly extends Fragment implements View.OnClickListener,AdapterView.OnItemClickListener{
    ArrayList<String> mItems;
    ArrayAdapter<String> adapter;
    TextView textYear;
    TextView textMon;
    TextView textTest;

    public Weekly(){}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.weekly,container,false);
/*        //오늘 날짜
        long now = System.currentTimeMillis();
        final Date date_today = new Date(now);

        //연,월,일을 따로 저장
        final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
        final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);
        final SimpleDateFormat curHourFormat = new SimpleDateFormat("h:mm a", Locale.KOREA);*/

        //textYear = (TextView)view.findViewById(R.id.edit1_w);
        //textMon = (TextView) view.findViewById(R.id.edit2_w);
        //textTest=(TextView)view.findViewById(R.id.edit3_w);
        mItems = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(inflater.getContext(),
                android.R.layout.simple_list_item_1, mItems);

        GridView gird = (GridView)view.findViewById(R.id.grid1_w);
        gird.setAdapter(adapter);
        gird.setOnItemClickListener(this);

        Date date = new Date();
        int year = date.getYear() + 1900;
        int mon = date.getMonth() + 1;

        int day1=date.getDate();

        //textYear.setText(year + "");
        //textMon.setText(mon + "");
        //textTest.setText(day1 + "");

        fillDate(year, mon);

        //Button btnmove = (Button) view.findViewById(R.id.bt1_w);
        //btnmove.setOnClickListener(this);

        return view;
    }
       @Override
     public void onClick(View arg0) {
         /* // TODO Auto-generated method stub
          if (arg0.getId() == R.id.bt1_w) {
              int year = Integer.parseInt(textYear.getText().toString());
              int mon = Integer.parseInt(textMon.getText().toString());
              fillDate(year, mon);
          }*/
      }
    private void fillDate(int year, int mon) {
        mItems.clear();

        mItems.add("일");    //0
        mItems.add("월");    //1
        mItems.add("화");    //2
        mItems.add("수");    //3
        mItems.add("목");    //4
        mItems.add("금");    //5
        mItems.add("토");    //6
        //오늘에 해당하는 요일 찾기
        Date current = new Date();
        int day = current.getDay();
        //오늘 날짜
        Date today =new Date();
        int last = today.getDate();
        //오늘이 해당하는 이번주 요일
        for (int i = last-day; i <= last+6-day; i++) {
            mItems.add(i + "");
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub
        if (mItems.get(arg2).equals("")) {
        } else {
            Intent intent = new Intent(getActivity(), Schedule_list.class);
            intent.putExtra("Param1", mItems.get(arg2));
            Log.v("test3", intent.toString());
            startActivity(intent);
        }
    }
}
