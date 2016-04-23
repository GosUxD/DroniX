package com.example.daniel.dronix.model.api;

import com.example.daniel.dronix.model.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by daniel on 23.4.16.
 */
public class RestApiManager {

    WeatherApi mWeatherApi;
    DronesApi mDronesApi;
    RiskApi mRiskApi;
    NoFlyApi mNoFlyApi;

    public WeatherApi getWeatherApi() {
        if(mWeatherApi == null) {
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            mWeatherApi = client.create(WeatherApi.class);
        }
        return  mWeatherApi;
    }

    public DronesApi getDronesApi() {
        if(mDronesApi == null) {
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_DRONES_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mDronesApi = client.create(DronesApi.class);
        }
        return mDronesApi;
    }

    public RiskApi getRiskApi() {
        if(mRiskApi == null) {
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mRiskApi = client.create(RiskApi.class);
        }
        return mRiskApi;
    }

    public NoFlyApi getNoFlyApi() {
        if(mNoFlyApi == null) {
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_NO_FLY_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mNoFlyApi = client.create(NoFlyApi.class);
        }
        return mNoFlyApi;
    }

}
