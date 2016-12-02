package com.example.mh.calendarproject;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

        ImageView pre_button = (ImageView) view.findViewById(R.id.calendar_prev_button);
        ImageView next_button = (ImageView) view.findViewById(R.id.calendar_next_button);
        today_text = (TextView) view.findViewById(R.id.calendar_date_display);
        list = (ListView) view.findViewById(R.id.list_item);

        todayCalendar = Calendar.getInstance();

        year=todayCalendar.get(Calendar.YEAR);
        mon=todayCalendar.get((Calendar.MONTH));
        date=todayCalendar.get(Calendar.DATE);

        String today = (year) +"/"+ (mon+1) +"/"+(date);

        setCalendar(todayCalendar);
        initListView(today);
        Log.i("test_daily",String.valueOf(today));

        pre_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todayCalendar = getLastDay(todayCalendar);
                setCalendar(todayCalendar);
            }
        });

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todayCalendar = getNextDay(todayCalendar);
                setCalendar(todayCalendar);
            }
        });

        return view;
    }

    public void initListView(String today) {
        MyDBHelper db = new MyDBHelper(getContext(), "Today.db", null, 1);
        Cursor cursor = db.getReadableDatabase().rawQuery("SELECT * FROM today WHERE date = '" + today + "'",null);
        cursor.moveToFirst();
        if(cursor.moveToFirst() == false) {
            list.getEmptyView();
        } else {
            mListMember = new ArrayList<String>();

            while (!cursor.isAfterLast()) {
                mListMember.add(cursor.getString(cursor.getColumnIndex("title")));
                cursor.moveToNext();
            }
            cursor.close();
            adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, mListMember);
            list.setAdapter(adapter);
            //list.setOnItemClickListener(this);
        }
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