package com.example.daniel.dronix.view.activities;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.example.daniel.dronix.R;

public class SplashActivity extends AppCompatActivity {

    private ImageView mClouds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);


        mClouds = (ImageView) findViewById(R.id.splash_clouds);

        ObjectAnimator animator3 = ObjectAnimator.ofFloat(mClouds, View.TRANSLATION_X, -1000);
        animator3.setInterpolator(new AccelerateInterpolator(1f));
        animator3.setDuration(8000);

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animator3);

        animatorSet.start();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();


    }
}
