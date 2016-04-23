
package com.example.daniel.dronix.model.pojo.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Elevation {

    @SerializedName("elevation")
    @Expose
    private double elevation;

    /**
     * 
     * @return
     *     The elevation
     */
    public double getElevation() {
        return elevation;
    }

    /**
     * 
     * @param elevation
     *     The elevation
     */
    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

}
