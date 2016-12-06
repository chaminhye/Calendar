package com.example.mh.calendarproject;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;


public class Daily extends Fragment {

    TextView today_text;
    ArrayList<String> mListMember;
    ArrayAdapter<String> adapter;
    private int year, mon, date;
    Calendar todayCalendar;
    ListView list;

    public Daily(){}
    @Nullable
    //@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_daily,container,false);

        ActionBar actionBar=((MainActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Daily Schedule");

        ImageView pre_button = (ImageView) view.findViewById(R.id.calendar_prev_button);
        ImageView next_button = (ImageView) view.findViewById(R.id.calendar_next_button);
        today_text = (TextView) view.findViewById(R.id.calendar_date_display);
        list = (ListView) view.findViewById(R.id.list_item);

        todayCalendar = Calendar.getInstance();

        year=todayCalendar.get(Calendar.YEAR);
        mon=todayCalendar.get((Calendar.MONTH))+1;
        date=todayCalendar.get(Calendar.DATE);

        String today = (year) +"/"+ (mon) +"/"+(date);
        Log.i("test_month", String.valueOf(todayCalendar.MONTH));
        Log.i("test_month", String.valueOf(mon));

        setCalendar(todayCalendar);

        Log.i("test_next",String.valueOf(year)+"/"+String.valueOf(mon)+"/"+String.valueOf(date));
        Log.i("test_daily",String.valueOf(today));
        initListView(today);

        pre_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todayCalendar = getLastDay(todayCalendar);
                setCalendar(todayCalendar);
                String last_day = todayCalendar.get(Calendar.YEAR) +"/"+ (todayCalendar.get(Calendar.MONTH)+1) +"/"+todayCalendar.get(Calendar.DATE);
                initListView(last_day);
            }
        });

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todayCalendar = getNextDay(todayCalendar);
                setCalendar(todayCalendar);
                String next_day = todayCalendar.get(Calendar.YEAR) +"/"+ (todayCalendar.get(Calendar.MONTH)+1) +"/"+todayCalendar.get(Calendar.DATE);
                initListView(next_day);
                Log.i("test_next11",String.valueOf(next_day));
            }
        });

        return view;
    }

    public void initListView(String today) {
        MyDBHelper db = new MyDBHelper(getContext(), "Today_edit.db", null, 5);
        Cursor cursor = db.getReadableDatabase().rawQuery("SELECT * FROM today_edit WHERE date = '" + today + "'",null);
        cursor.moveToFirst();
        mListMember = new ArrayList<String>();

        while(!cursor.isAfterLast()) {
            mListMember.add(cursor.getString(cursor.getColumnIndex("title")));
            cursor.moveToNext();
        }
        cursor.close();
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, mListMember);
        list.setAdapter(adapter);
        //list.setOnItemClickListener(this);
    }

    private void setCalendar (Calendar calendar) {
        today_text.setText(String.valueOf(calendar.get(Calendar.YEAR))+ "/" +String.valueOf(calendar.get(Calendar.MONTH)+1)+ "/" +String.valueOf(calendar.get(Calendar.DATE)));
    }

    private Calendar getLastDay(Calendar calendar)
    {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        calendar.add(Calendar.DATE, -1);

        return calendar;

    }

    private Calendar getNextDay(Calendar calendar)
    {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        calendar.add(Calendar.DATE, +1);

        return calendar;
    }

}