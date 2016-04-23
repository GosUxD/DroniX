package com.example.daniel.dronix.model.api;

import com.example.daniel.dronix.model.pojo.Drones.DroneResponse;
import com.example.daniel.dronix.model.pojo.Locations.NoFlyZone;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by daniel on 23.4.16.
 */
public interface NoFlyApi {

    @GET("nearby/")
    Call<List<NoFlyZone>> getNoFlyZoness(@Query("lat") Double lat,
                                         @Query("lng") Double lng);

}
