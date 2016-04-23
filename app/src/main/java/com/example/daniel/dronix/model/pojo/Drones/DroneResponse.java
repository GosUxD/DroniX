
package com.example.daniel.dronix.model.pojo.Drones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DroneResponse {

    @SerializedName("ID")
    @Expose
    private int ID;
    @SerializedName("Name")
    @Expose
    private String Name;
    @SerializedName("DeviceToken")
    @Expose
    private String DeviceToken;
    @SerializedName("Coordinate")
    @Expose
    private com.example.daniel.dronix.model.pojo.Drones.Coordinate Coordinate;
    @SerializedName("LastActive")
    @Expose
    private String LastActive;

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
     *     The Name
     */
    public String getName() {
        return Name;
    }

    /**
     * 
     * @param Name
     *     The Name
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * 
     * @return
     *     The DeviceToken
     */
    public String getDeviceToken() {
        return DeviceToken;
    }

    /**
     * 
     * @param DeviceToken
     *     The DeviceToken
     */
    public void setDeviceToken(String DeviceToken) {
        this.DeviceToken = DeviceToken;
    }

    /**
     * 
     * @return
     *     The Coordinate
     */
    public com.example.daniel.dronix.model.pojo.Drones.Coordinate getCoordinate() {
        return Coordinate;
    }

    /**
     * 
     * @param Coordinate
     *     The Coordinate
     */
    public void setCoordinate(com.example.daniel.dronix.model.pojo.Drones.Coordinate Coordinate) {
        this.Coordinate = Coordinate;
    }

    /**
     * 
     * @return
     *     The LastActive
     */
    public String getLastActive() {
        return LastActive;
    }

    /**
     * 
     * @param LastActive
     *     The LastActive
     */
    public void setLastActive(String LastActive) {
        this.LastActive = LastActive;
    }

}
