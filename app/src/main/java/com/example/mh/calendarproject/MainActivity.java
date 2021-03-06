package com.example.mh.calendarproject;

import android.app.ActionBar;
import android.content.Intent;
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
    final ScheduleEdit_Activity FD= new ScheduleEdit_Activity();

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
                return true;
            case R.id.fb:
                getSupportFragmentManager().beginTransaction().replace(R.id.main, FB).addToBackStack(null).commit();
                return true;
            case R.id.fc:
                getSupportFragmentManager().beginTransaction().replace(R.id.main, FC).addToBackStack(null).commit();
                return true;
            case R.id.fd:
                startActivity(new Intent(this, ScheduleEdit_Activity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
