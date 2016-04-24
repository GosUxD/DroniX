package com.example.daniel.dronix.view.activities;

import android.Manifest;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.daniel.dronix.R;
import com.example.daniel.dronix.model.pojo.Drones.DroneResponse;
import com.example.daniel.dronix.model.pojo.Locations.NoFlyZone;
import com.example.daniel.dronix.model.pojo.Weather.Probability;
import com.example.daniel.dronix.model.pojo.Weather.WeatherResponse;
import com.example.daniel.dronix.presenter.Presenter;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.util.List;

public class EarthActivity extends AppCompatActivity implements View.OnClickListener, Presenter.OnWeatherCallback {

    private static final String TAG = "Dan";
    private FloatingActionButton mWeather;
    private FloatingActionButton mDrones;
    private FloatingActionButton mLocations;
    private FloatingActionMenu mFabMenu;
    private GoogleApiClient mClient;
    private MapView mMapView;
    private GoogleMap mMap;
    private boolean mIsDronesOn = false, mIsWeatherOn = false, mIsLocatinsOn = false;
    private Presenter mPresenter;
    LatLng myPosition = new LatLng(42.0018529, 21.3881011);
    BitmapDescriptor itemBitmap;
    private Toolbar toolbar;
    private LinearLayout mWeatherContaier;
    private TextView mWindSpeed, mVisibility, mHumidity, mWindDirection, mTemp;
    private static int radius = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earth_map);
        MapsInitializer.initialize(this);

        mMapView = (MapView) findViewById(R.id.map_view);
        mMapView.onCreate(savedInstanceState);

        mClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(@Nullable Bundle bundle) {
                        Log.i("Dan", "GoogleApiClient connected");

                    }

                    @Override
                    public void onConnectionSuspended(int i) {

                    }
                })
                .addApi(LocationServices.API)
                .build();
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                Log.i("Dan", "MapReady");
                drawUserOnMap();
            }
        });
        initUI();

    }

    private void initUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mFabMenu = (FloatingActionMenu) findViewById(R.id.menu);
        mDrones = (FloatingActionButton) findViewById(R.id.menu_drones);
        mWeather = (FloatingActionButton) findViewById(R.id.menu_weather);
        mLocations = (FloatingActionButton) findViewById(R.id.menu_locations);
        mDrones.setOnClickListener(this);
        mFabMenu.setOnClickListener(this);
        mLocations.setOnClickListener(this);
        mWeather.setOnClickListener(this);
        mWeatherContaier = (LinearLayout) findViewById(R.id.weather_container);

        mTemp = (TextView) findViewById(R.id.temp_text_view);
        mVisibility = (TextView) findViewById(R.id.visibility_text_vew);
        mWindSpeed = (TextView) findViewById(R.id.wind_speed_text_view);
        mWindDirection = (TextView) findViewById(R.id.wind_direction);
        mHumidity = (TextView) findViewById(R.id.humidity_text_view);


        itemBitmap = BitmapDescriptorFactory.fromResource(R.drawable.drone_map);

        mPresenter = new Presenter(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_drones:
                Log.i("Dan", "Show drones");
                mPresenter.getDronesInfo(myPosition.latitude, myPosition.longitude);
                break;
            case R.id.menu_weather:
                if (mIsWeatherOn) {
                    mWeatherContaier.setVisibility(View.GONE);
                    mIsWeatherOn = false;
                } else {
                    mPresenter.getWeatherInfo(myPosition.latitude, myPosition.longitude);
                    mIsWeatherOn = true;
                }
;
                break;
            case R.id.menu_locations:
                mPresenter.getNoFlyZonesInfo(myPosition.latitude, myPosition.longitude);
                break;
        }
    }

    private void notifyUser(Uri sound) {
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(), 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker("Drones Nearby")
                .setSmallIcon(R.drawable.drone_map)
                .setContentTitle("Warning")
                .setContentText("There might be drones nearby, be careful")
                .setContentIntent(pi)
                .setSound(sound)
                .setVibrate(new long[]{500, 1000})
                .build();

        NotificationManagerCompat notifManager = NotificationManagerCompat.from(this);
        notifManager.notify(0, notification);
    }

    private void notifyUserNoFlyZone() {
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(), 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker("No Fly Zone Nearby")
                .setSmallIcon(R.drawable.danger)
                .setContentTitle("Warning")
                .setContentText("There are no fly zone nearby, fly with caution")
                .setContentIntent(pi)
                .setVibrate(new long[]{500, 1000})
                .build();

        NotificationManagerCompat notifManager = NotificationManagerCompat.from(this);
        notifManager.notify(0, notification);
    }


    private void drawUserOnMap() {
        LatLng myPoint = myPosition;
        LatLng bounds = new LatLng(myPoint.latitude, myPoint.longitude);

        MarkerOptions myPosition = new MarkerOptions().position(myPoint);
        mMap.addMarker(myPosition);

        CircleOptions circleOptions = new CircleOptions()
                .center(myPoint)
                .fillColor(0x5500ff00)
                .strokeColor(0x55000000)
                .strokeWidth(1)
                .radius(radius); // In meters
        mMap.addCircle(circleOptions);

        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(bounds, 11.5f);
        mMap.animateCamera(update);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mClient.connect();

    }

    @Override
    protected void onStop() {
        super.onStop();
        mClient.disconnect();
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void OnWeatherSuccess(WeatherResponse weatherResponse) {
        Log.i("Dan", weatherResponse.getLocation().getCity());
            mWeatherContaier.setVisibility(View.VISIBLE);
            mVisibility.setText(weatherResponse.getAtmosphere().getVisibility() + " " + weatherResponse.getUnit().getDistance());
            mWindSpeed.setText(+ weatherResponse.getWind().getSpeed() + " " + weatherResponse.getUnit().getSpeed());
            mHumidity.setText( weatherResponse.getAtmosphere().getHumidity() + "%");
            mTemp.setText(weatherResponse.getCondition().getTemp() + " " +weatherResponse.getUnit().getTemperature());
    }

    @Override
    public void OnWeatherFailed(String error) {
        Log.i("Dan", error);
    }

    @Override
    public void OnDroneSuccess(List<DroneResponse> droneResponseList) {
        Log.i("Dan", "Drone");
        mMap.clear();
        drawUserOnMap();
        for(DroneResponse currDrone : droneResponseList) {
            MarkerOptions drone = new MarkerOptions().position(new LatLng(currDrone.getCoordinate().getLatitude(), currDrone.getCoordinate().getLongitude()));
            drone.icon(itemBitmap);
            mMap.addMarker(drone);
        }



        Uri sound = Uri.parse("android.resource://com.example.daniel.dronix/" + R.raw.beep);

        notifyUser(sound);

    }

    @Override
    public void OnDroneFailed() {

    }

    @Override
    public void OnNoFlyZoneSuccess(List<NoFlyZone> noFlyZones) {
        mMap.clear();
        drawUserOnMapZoomedOut();
        for(NoFlyZone currFlyZone : noFlyZones) {
            CircleOptions zone = new CircleOptions().center(new LatLng(currFlyZone.getCoordinate().getLatitude(), currFlyZone.getCoordinate().getLongitude()));
            zone.radius(currFlyZone.getNoFlyCategoryKm()*1000)
                    .fillColor(0x33FF0008)
                    .strokeColor(0x33FF0008)
                    .strokeWidth(1);

            mMap.addCircle(zone);
        }
        notifyUserNoFlyZone();
    }

    @Override
    public void OnProbabilitySuccess(Probability probability) {

    }

    public void drawUserOnMapZoomedOut() {
        LatLng myPoint = myPosition;
        LatLng bounds = new LatLng(myPoint.latitude, myPoint.longitude);

        MarkerOptions myPosition = new MarkerOptions().position(myPoint);
        mMap.addMarker(myPosition);

        CircleOptions circleOptions = new CircleOptions()
                .center(myPoint)
                .fillColor(0x5500ff00)
                .strokeColor(0x55000000)
                .strokeWidth(1)
                .radius(radius); // In meters
        mMap.addCircle(circleOptions);

        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(bounds, 9.8f);
        mMap.animateCamera(update);
    }

}
