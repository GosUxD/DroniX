package com.example.daniel.dronix.model.pojo.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit2.http.FormUrlEncoded;

/**
 * Created by daniel on 23.4.16.
 */
public class Probability {


    @SerializedName("probability")
    @Expose
    double probability;

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }
}
