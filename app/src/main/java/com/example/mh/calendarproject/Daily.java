package com.example.mh.calendarproject;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Date;


public class Daily extends Fragment {

    TextView today_text;
    ArrayList<String> mListMember = new ArrayList<String>();
    ListView mListItem;
    Cursor mCursor;

    EditText editDate, editTitle, editTime, editMemo;

    public Daily(){}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_daily,container,false);

        ImageView pre_button = (ImageView) view.findViewById(R.id.calendar_prev_button);
        ImageView next_button = (ImageView) view.findViewById(R.id.calendar_next_button);
        today_text = (TextView) view.findViewById(R.id.calendar_date_display);

        Date today = new Date();
        int year = today.getYear() + 1900;
        int mon = today.getMonth() + 1;
        int current = today.getDate();
        today_text.setText(String.valueOf(year)+"/"+String.valueOf(mon)+"/"+String.valueOf(current));

//        initListView(today);

        return view;
    }




//    public void initListView(Date date) {
//        // 어댑터 생성
//        adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, mListMember);
//        // ListView 와 어댑터를 연결
//        mListItem = (ListView)findViewById(R.id.list_item);
//        mListItem.setAdapter(adapter);
//        mListItem.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//        mListItem.setDivider(new ColorDrawable(Color.GRAY));
//        mListItem.setDividerHeight(2);
//        mListItem.setOnItemClickListener(mItemListener);
//    }

    AdapterView.OnItemClickListener mItemListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            viewRecord(position);
        }
    };

    public void viewRecord(int nIndex) {
        // 선택된 항목의 필드 내용을 구한다
        mCursor.moveToPosition(nIndex);

        // 필드 내용을 화면에 표시
        editTitle.setText(mCursor.getString(1));
        editDate.setText(mCursor.getString(2));
        editTime.setText(mCursor.getString(3));
        editMemo.setText(mCursor.getString(4));
    }

}