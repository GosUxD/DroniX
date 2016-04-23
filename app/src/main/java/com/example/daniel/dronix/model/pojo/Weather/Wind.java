
package com.example.daniel.dronix.model.pojo.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("chill")
    @Expose
    private int chill;
    @SerializedName("direction")
    @Expose
    private int direction;
    @SerializedName("speed")
    @Expose
    private double speed;
    @SerializedName("directionSymbol")
    @Expose
    private String directionSymbol;

    public String getDirectionSymbol() {
        return directionSymbol;
    }

    /**
     * @param directionSymbol The directionSymbol
     */
    public void setDirectionSymbol(String directionSymbol) {
        this.directionSymbol = directionSymbol;
    }

    /**
     * @return The chill
     */
    public int getChill() {
        return chill;
    }

    /**
     * @param chill The chill
     */
    public void setChill(int chill) {
        this.chill = chill;
    }

    /**
     * @return The direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * @param direction The direction
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * @return The speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * @param speed The speed
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

}
