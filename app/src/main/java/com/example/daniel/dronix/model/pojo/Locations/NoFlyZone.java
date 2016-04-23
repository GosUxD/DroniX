
package com.example.daniel.dronix.model.pojo.Locations;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NoFlyZone {

    @SerializedName("ID")
    @Expose
    private int ID;
    @SerializedName("Coordinate")
    @Expose
    private com.example.daniel.dronix.model.pojo.Locations.Coordinate Coordinate;
    @SerializedName("NoFlyZoneName")
    @Expose
    private String NoFlyZoneName;
    @SerializedName("NoFlyCategory")
    @Expose
    private int NoFlyCategory;
    @SerializedName("NoFlyCategoryKm")
    @Expose
    private double NoFlyCategoryKm;
    @SerializedName("Distance")
    @Expose
    private double Distance;

    public double getDistance() {
        return Distance;
    }

    public void setDistance(double distance) {
        Distance = distance;
    }

    /**
     * 
     * @return
     *     The ID

     */
    public int getID() {
        return ID;
    }

    /**
     * 
     * @param ID
     *     The ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * 
     * @return
     *     The Coordinate
     */
    public com.example.daniel.dronix.model.pojo.Locations.Coordinate getCoordinate() {
        return Coordinate;
    }

    /**
     * 
     * @param Coordinate
     *     The Coordinate
     */
    public void setCoordinate(com.example.daniel.dronix.model.pojo.Locations.Coordinate Coordinate) {
        this.Coordinate = Coordinate;
    }

    /**
     * 
     * @return
     *     The NoFlyZoneName
     */
    public String getNoFlyZoneName() {
        return NoFlyZoneName;
    }

    /**
     * 
     * @param NoFlyZoneName
     *     The NoFlyZoneName
     */
    public void setNoFlyZoneName(String NoFlyZoneName) {
        this.NoFlyZoneName = NoFlyZoneName;
    }

    /**
     * 
     * @return
     *     The NoFlyCategory
     */
    public int getNoFlyCategory() {
        return NoFlyCategory;
    }

    /**
     * 
     * @param NoFlyCategory
     *     The NoFlyCategory
     */
    public void setNoFlyCategory(int NoFlyCategory) {
        this.NoFlyCategory = NoFlyCategory;
    }

    /**
     * 
     * @return
     *     The NoFlyCategoryKm
     */
    public double getNoFlyCategoryKm() {
        return NoFlyCategoryKm;
    }

    /**
     * 
     * @param NoFlyCategoryKm
     *     The NoFlyCategoryKm
     */
    public void setNoFlyCategoryKm(double NoFlyCategoryKm) {
        this.NoFlyCategoryKm = NoFlyCategoryKm;
    }

}
