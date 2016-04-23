
package com.example.daniel.dronix.model.pojo.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherResponse {

    @SerializedName("Unit")
    @Expose
    private com.example.daniel.dronix.model.pojo.Weather.Unit Unit;
    @SerializedName("Wind")
    @Expose
    private com.example.daniel.dronix.model.pojo.Weather.Wind Wind;
    @SerializedName("Location")
    @Expose
    private com.example.daniel.dronix.model.pojo.Weather.Location Location;
    @SerializedName("Atmosphere")
    @Expose
    private com.example.daniel.dronix.model.pojo.Weather.Atmosphere Atmosphere;
    @SerializedName("Elevation")
    @Expose
    private com.example.daniel.dronix.model.pojo.Weather.Elevation Elevation;

    public com.example.daniel.dronix.model.pojo.Weather.Condition getCondition() {
        return Condition;
    }

    public void setCondition(com.example.daniel.dronix.model.pojo.Weather.Condition condition) {
        Condition = condition;
    }

    @SerializedName("Condition")
    @Expose
    private com.example.daniel.dronix.model.pojo.Weather.Condition Condition;

    public com.example.daniel.dronix.model.pojo.Weather.Unit getUnit() {
        return Unit;
    }

    public void setUnit(com.example.daniel.dronix.model.pojo.Weather.Unit unit) {
        Unit = unit;
    }

    /**
     * @return The Wind
     */
    public com.example.daniel.dronix.model.pojo.Weather.Wind getWind() {
        return Wind;
    }

    /**
     * @param Wind The Wind
     */
    public void setWind(com.example.daniel.dronix.model.pojo.Weather.Wind Wind) {
        this.Wind = Wind;
    }

    /**
     * @return The Location
     */
    public com.example.daniel.dronix.model.pojo.Weather.Location getLocation() {
        return Location;
    }

    /**
     * @param Location The Location
     */
    public void setLocation(com.example.daniel.dronix.model.pojo.Weather.Location Location) {
        this.Location = Location;
    }

    /**
     * @return The Atmosphere
     */
    public com.example.daniel.dronix.model.pojo.Weather.Atmosphere getAtmosphere() {
        return Atmosphere;
    }

    /**
     * @param Atmosphere The Atmosphere
     */
    public void setAtmosphere(com.example.daniel.dronix.model.pojo.Weather.Atmosphere Atmosphere) {
        this.Atmosphere = Atmosphere;
    }

    /**
     * @return The Elevation
     */
    public com.example.daniel.dronix.model.pojo.Weather.Elevation getElevation() {
        return Elevation;
    }

    /**
     * @param Elevation The Elevation
     */
    public void setElevation(com.example.daniel.dronix.model.pojo.Weather.Elevation Elevation) {
        this.Elevation = Elevation;
    }

}
