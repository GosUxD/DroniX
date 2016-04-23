package com.example.daniel.dronix.model;

/**
 * Created by daniel on 23.4.16.
 */
public class Storage {

    double humidity,windspeed,visibility;
    int numDrones;
    String condition;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(double windspeed) {
        this.windspeed = windspeed;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public int getNumDrones() {
        return numDrones;
    }

    public void setNumDrones(int numDrones) {
        this.numDrones = numDrones;
    }

    private static Storage ourInstance = new Storage();

    public static Storage getInstance() {
        return ourInstance;
    }

    private Storage() {
    }
}
