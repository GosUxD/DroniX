package com.example.daniel.dronix.model.api;

import com.example.daniel.dronix.model.pojo.Weather.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by daniel on 23.4.16.
 */
public interface WeatherApi {

    @GET("GetDroneInfo/")
    Call<WeatherResponse> getWeather(@Query("lat") Double lat,
                                     @Query("lng") Double lng);

}
