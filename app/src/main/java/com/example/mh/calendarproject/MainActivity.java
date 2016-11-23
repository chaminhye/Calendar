package com.example.mh.calendarproject;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    final Monthly FA = new Monthly();
    final Weekly FB = new Weekly();
    final Daily FC = new Daily();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            Drawable drawable = getDrawable(R.drawable.back);
            if (drawable != null) {
                drawable.setTint(Color.WHITE);
                actionBar.setHomeAsUpIndicator(drawable);
            }
        }
/*        CalendarView calendar=(CalendarView)findViewById(R.id.calendar);

        calendar.setOnDateChangeListener((new CalendarView.OnDateChangeListener(){
            public void onSelectedDayChange(CalendarView view, int year,int month, int dayOfMonth){
                //Log.i("View.getDate", view.getDate() + "");
                //Calendar calendar =Calendar.getInstance();
               // calendar.set(year,month,dayOfMonth);
                //int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            }
        }));*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.fa:
                getSupportFragmentManager().beginTransaction().replace(R.id.main, FA).addToBackStack(null).commit();
                Log.i("dd","213");
                return true;
            case R.id.fb:
                getSupportFragmentManager().beginTransaction().replace(R.id.main, FB).addToBackStack(null).commit();
                return true;
            case R.id.fc:
                getSupportFragmentManager().beginTransaction().replace(R.id.main, FC).addToBackStack(null).commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
