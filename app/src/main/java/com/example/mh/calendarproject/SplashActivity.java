package com.example.mh.calendarproject;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

/**
 * Created by mh on 2016-11-22.
 */

public class SplashActivity extends Activity {
    int SPLASH_TIME=4000;
    ImageView mCal;
    int mScreenHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mCal=(ImageView)findViewById(R.id.cal);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                overridePendingTransition(0,android.R.anim.fade_in);
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        },SPLASH_TIME);
    }
    protected void onResume(){      //애니메이션 실행되는 부분
        super.onResume();
        DisplayMetrics displaymetrics = new DisplayMetrics();   //화면정보(Height)를 읽어옴
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        mScreenHeight=displaymetrics.heightPixels;

        startFireValuePropertyAnimation();
    }
    private void startFireValuePropertyAnimation() {
        ValueAnimator scaleAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        scaleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (float) valueAnimator.getAnimatedValue();
                mCal.setScaleX(value);
                mCal.setScaleY(value);
            }
        });

        ValueAnimator scaleAnimator2 = ValueAnimator.ofFloat(1.0f,0.0f);
        scaleAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (float) valueAnimator.getAnimatedValue();
                mCal.setAlpha(value);

            }
        });

        scaleAnimator.setInterpolator(new DecelerateInterpolator());
        scaleAnimator2.setInterpolator(new DecelerateInterpolator());

        scaleAnimator.setDuration(2000);;
        scaleAnimator.setStartDelay(0000);

        scaleAnimator2.setDuration(2000);;
        scaleAnimator2.setStartDelay(2000);

        scaleAnimator.start();
        scaleAnimator2.start();

    }
}
