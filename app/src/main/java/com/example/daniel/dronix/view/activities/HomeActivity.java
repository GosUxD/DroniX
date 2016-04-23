package com.example.daniel.dronix.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daniel.dronix.R;
import com.example.daniel.dronix.model.pojo.Drones.DroneResponse;
import com.example.daniel.dronix.model.pojo.Locations.NoFlyZone;
import com.example.daniel.dronix.model.pojo.Weather.Probability;
import com.example.daniel.dronix.model.pojo.Weather.WeatherResponse;
import com.example.daniel.dronix.presenter.Presenter;
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements Presenter.OnWeatherCallback {

    ImageView mEarthMap;
    Toolbar mToolbar;
    ArcProgress mArcProgress;
    Presenter mPresenter;
    LatLng myPosition = new LatLng(42.0018529, 21.3881011);
    TextView mLat, mLong, mCityState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        mPresenter = new Presenter(this);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mArcProgress  = (ArcProgress) findViewById(R.id.donut_progress);

        mPresenter.getWeatherInfo(myPosition.latitude,myPosition.longitude);

        mEarthMap = (ImageView) findViewById(R.id.ladning_toggle_map);
        mEarthMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, EarthActivity.class);
                startActivity(intent);
            }
        });


        mLat = (TextView) findViewById(R.id.lat_text);
        mLong = (TextView) findViewById(R.id.long_text);
        mCityState = (TextView) findViewById(R.id.location_text);
    }

    @Override
    public void OnWeatherSuccess(WeatherResponse weatherResponse) {

        double humidity = weatherResponse.getAtmosphere().getHumidity();
        double windSpeed = weatherResponse.getWind().getSpeed();
        double visibility = weatherResponse.getAtmosphere().getVisibility();
        String condition = weatherResponse.getCondition().getText();



        mPresenter.getProbability(humidity,windSpeed,visibility,condition,myPosition.latitude,myPosition.longitude);


    }

    @Override
    public void OnWeatherFailed(String error) {

    }

    @Override
    public void OnDroneSuccess(List<DroneResponse> resultDrones) {

    }

    @Override
    public void OnDroneFailed() {

    }

    @Override
    public void OnNoFlyZoneSuccess(List<NoFlyZone> noFlyZones) {

    }

    @Override
    public void OnProbabilitySuccess(Probability probability) {

        mArcProgress.setProgress((int) probability.getProbability());

    }
}
