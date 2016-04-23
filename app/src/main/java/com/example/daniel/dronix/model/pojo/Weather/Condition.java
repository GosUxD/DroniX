package com.example.daniel.dronix.model.pojo.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by daniel on 23.4.16.
 */
public class Condition {

    @SerializedName("temp")
    @Expose
    int temp;

    @SerializedName("text")
    @Expose
    String text;

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
