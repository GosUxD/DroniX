package com.example.daniel.dronix.presenter;

import android.util.Log;

import com.example.daniel.dronix.model.api.RestApiManager;
import com.example.daniel.dronix.model.pojo.Drones.DroneResponse;
import com.example.daniel.dronix.model.pojo.Locations.NoFlyZone;
import com.example.daniel.dronix.model.pojo.Weather.Probability;
import com.example.daniel.dronix.model.pojo.Weather.WeatherResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter {

    OnWeatherCallback mOnWeatherCallback;
    RestApiManager mRestApiManager;

    public Presenter(OnWeatherCallback onWeatherCallback) {
        mOnWeatherCallback = onWeatherCallback;
        mRestApiManager = new RestApiManager();
    }


    public void getWeatherInfo(Double lat, Double lng) {
        mRestApiManager.getWeatherApi().getWeather(lat, lng).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                WeatherResponse weatherResponse = response.body();
                Log.i("Dan", "Presenter OnResponse");
                mOnWeatherCallback.OnWeatherSuccess(weatherResponse);


            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.i("Dan", "Preseter OnFailure");
                mOnWeatherCallback.OnWeatherFailed(t.toString());
            }
        });
    }




    public void getDronesInfo(Double lat, Double lng) {
        mRestApiManager.getDronesApi().getDrones(lat, lng).enqueue(new Callback<List<DroneResponse>>() {
            @Override
            public void onResponse(Call<List<DroneResponse>> call, Response<List<DroneResponse>> response) {
                List<DroneResponse> drones =  response.body();
                mOnWeatherCallback.OnDroneSuccess(drones);
            }

            @Override
            public void onFailure(Call<List<DroneResponse>> call, Throwable t) {
                Log.i("Dan", t.toString());
            }
        });
    }

    public void getNoFlyZonesInfo(Double lat, Double lng) {
        mRestApiManager.getNoFlyApi().getNoFlyZoness(lat, lng).enqueue(new Callback<List<NoFlyZone>>() {
            @Override
            public void onResponse(Call<List<NoFlyZone>> call, Response<List<NoFlyZone>> response) {
                List<NoFlyZone> noFlyZones = response.body();
                mOnWeatherCallback.OnNoFlyZoneSuccess(noFlyZones);

                for(NoFlyZone z : noFlyZones){
                    Log.i("Dan", z.getCoordinate().getLatitude()+" "+z.getCoordinate().getLongitude());
                }

            }

            @Override
            public void onFailure(Call<List<NoFlyZone>> call, Throwable t) {
                Log.i("Dan", t.toString());
            }
        });
    }

    public void getProbability( double humidity,double windSpeed, double visibility, String condition,Double lat, Double lng) {
        mRestApiManager.getRiskApi().getRisk(  humidity, windSpeed,  visibility, condition,lat,lng).enqueue(new Callback<Probability>() {
            @Override
            public void onResponse(Call<Probability> call, Response<Probability> response) {
               Probability probability = response.body();
                mOnWeatherCallback.OnProbabilitySuccess(probability);
            }

            @Override
            public void onFailure(Call<Probability> call, Throwable t) {

            }
        });
    }


    public interface OnWeatherCallback {
        void OnWeatherSuccess(WeatherResponse weatherResponse);

        void OnWeatherFailed(String error);

        void OnDroneSuccess(List<DroneResponse> resultDrones);

        void OnDroneFailed();

        void OnNoFlyZoneSuccess(List<NoFlyZone> noFlyZones);

        void OnProbabilitySuccess(Probability probability);
    }

}
