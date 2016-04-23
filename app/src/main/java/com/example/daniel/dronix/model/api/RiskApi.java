package com.example.daniel.dronix.model.api;

import com.example.daniel.dronix.model.pojo.Weather.Probability;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by daniel on 23.4.16.
 */
public interface RiskApi {

    @GET("Probability")
    Call<Probability> getRisk(@Query("humidity") double humidity,
                              @Query("windSpeed") double windSpeed,
                              @Query("visibility") double visibility,
                              @Query("condition") String condition,
                              @Query("lat") double latitude,
                              @Query("lng") double longitude);

}


