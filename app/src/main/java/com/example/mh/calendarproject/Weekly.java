package com.example.mh.calendarproject;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Sea on 2016-10-12.
 */
public class Weekly extends Fragment {
    static MyAdapter adapter;
    static MyAdapter adapter1;
    static MyAdapter adapter2;
    static int d=0;
    ArrayList<MyItem> data;
    ArrayList<String> mItems;
    private int last;
    private int last2;
    private int day,day10;
    private int YEAR1,MON1,DATE1,DATE2,DATE3;
    Calendar todayCalendar,todayCalendar1;
    static int cnt=0;

    public Weekly(){}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.weekly, container, false);

        ActionBar actionBar=((MainActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Weekly Schedule");

        mItems = new ArrayList<String>();
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
        day = current.getDay();

        todayCalendar = Calendar.getInstance();

        YEAR1=todayCalendar.get(Calendar.YEAR);
        MON1=todayCalendar.get((Calendar.MONTH))+1;
        DATE1=todayCalendar.get(Calendar.DATE);
        DATE2=todayCalendar.getMaximum(Calendar.DAY_OF_MONTH);
        Log.i("ZZZZZZZZ", String.valueOf(DATE2));
        //오늘 날짜
        Date today = new Date();
        last = today.getDate();
        //달의 마지막일을 확인하기위함
        current.setDate(32);
        last2 = 32 - current.getDate();



        data = new ArrayList<MyItem>();


        //오늘이 해당하는 이번주 요일
        for (int i = last - day, j = 0; i <= last + 6 - day; i++, j++) {
            if (i > last2 || i <= 0) {
//                todayCalendar=getLastDay(todayCalendar);
                data.add(new MyItem(mItems.get(j), i));
            } else {
                data.add(new MyItem(mItems.get(j), i));
            }
        }
        //어댑터 생성
        adapter = new MyAdapter(getContext(), R.layout.item, data);

        //어댑터 연결
        ListView listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setDivider(new ColorDrawable(Color.BLACK));
        listView.setDividerHeight(2);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View vClicked,
                                    int position, long id) {
                String name = ((MyItem) adapter.getItem(position)).nClass;
                int name1 = ((MyItem) adapter.getItem(position)).nDay;

                Intent intent = new Intent(getActivity(), Schedule_list.class);

                Date date = new Date();
                int year = date.getYear()+1900;
                int mon = date.getMonth() + 1;

                intent.putExtra("Param1",year+"/"+mon+"/"+ name1);
                startActivity(intent);
            }
        });


        ImageView btn1 = (ImageView) view.findViewById(R.id.btn1);   //지난주
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                last = last-7;
                data.clear();

                for(int i = last - day, j = 0; i <= last + 6 - day; i++, j++) {

                    if(i>0){
                        data.add(new MyItem(mItems.get(j), i));
                    }else{

                        todayCalendar1 = Calendar.getInstance();
                        todayCalendar1 = getLastDay(todayCalendar1);

                        DATE3 = todayCalendar1.getActualMaximum(Calendar.DAY_OF_MONTH);

                        Log.i("day_of", String.valueOf(DATE3));
                        data.add(new MyItem(mItems.get(j), i + DATE3));

                    }
                }

                //어댑터 생성
                adapter1 = new MyAdapter(getContext(), R.layout.item, data);

                //어댑터 연결
                ListView listView = (ListView) view.findViewById(R.id.listView);
                listView.setAdapter(adapter1);

                listView.setDivider(new ColorDrawable(Color.BLACK));
                listView.setDividerHeight(2);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View vClicked,
                                            int position, long id) {
                        String name = ((MyItem) adapter.getItem(position)).nClass;
                        int name1 = ((MyItem) adapter.getItem(position)).nDay;

                        Intent intent = new Intent(getActivity(), Schedule_list.class);

                        Date date = new Date();
                        int year = date.getYear()+1900;
                        int mon = date.getMonth() + 1;

                        intent.putExtra("Param1",year+"/"+mon+"/"+ name1);
                        startActivity(intent);

                    }
                });
            }
        });
        ImageView btn2 = (ImageView) view.findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {    //이번주
            @Override
            public void onClick(View v) {
                last = last + 7;
                data.clear();
                for(int i = last - day, j = 0; i <= last + 6 - day; i++, j++) {

                    if(i<=last2){
                        data.add(new MyItem(mItems.get(j), i));
                    }else{

                        todayCalendar1 = Calendar.getInstance();
                        todayCalendar1 = getLastDay(todayCalendar1);

                        DATE3 = todayCalendar1.getActualMaximum(Calendar.DAY_OF_MONTH);

                        Log.i("day_of", String.valueOf(DATE3));
                        data.add(new MyItem(mItems.get(j), i + DATE3));

                    }
                }

                //어댑터 생성
                adapter2 = new MyAdapter(getContext(), R.layout.item, data);

                //어댑터 연결
                ListView listView = (ListView) view.findViewById(R.id.listView);
                listView.setAdapter(adapter2);

                listView.setDivider(new ColorDrawable(Color.BLACK));
                listView.setDividerHeight(2);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View vClicked,
                                            int position, long id) {
                        String name = ((MyItem) adapter.getItem(position)).nClass;
                        int name1 = ((MyItem) adapter.getItem(position)).nDay;

                        Intent intent = new Intent(getActivity(), Schedule_list.class);

                        Date date = new Date();
                        int year = date.getYear()+1900;
                        int mon = date.getMonth() + 1;

                        intent.putExtra("Param1",year+"/"+mon+"/"+ name1);
                        startActivity(intent);

                    }
                });
            }
        });
        return view;
    }

    private Calendar getLastDay(Calendar calendar)
    {

        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        calendar.add(Calendar.MONTH, -1);
//        calendar.getMaximum(calendar.DAY_OF_MONTH);

        return calendar;
    }
    private Calendar getLastDay(Calendar calendar, int a)
    {

        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        calendar.add(Calendar.MONTH, -a);
//        calendar.getMaximum(calendar.DAY_OF_MONTH);

        return calendar;
    }
/*    private int getLastDay(int lastday)
    {

        todayCalendar = Calendar.getInstance();

        YEAR1=todayCalendar.get(Calendar.YEAR);
        MON1=todayCalendar.get((Calendar.MONTH))+1;
        DATE1=todayCalendar.get(Calendar.DATE)-1;

        return DATE1;
    }*/
    private Calendar getNextDay(Calendar calendar)
    {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.getActualMaximum(Calendar.DATE));
        calendar.add(Calendar.DATE, +1);

        return calendar;
    }

}
