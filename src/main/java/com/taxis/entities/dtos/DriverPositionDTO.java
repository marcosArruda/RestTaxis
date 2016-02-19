package com.taxis.entities.dtos;

import com.taxis.entities.DriverPosition;
import org.joda.time.Instant;

import java.io.Serializable;

/**
 * Created by marcosarruda on 2/15/16.
 */
public class DriverPositionDTO implements Serializable{

    private String driverId;
    private double latitude;
    private double longitude;
    private Boolean driverAvailable;

    /*
    POST /drivers/8475/status { "latitude":-23.60810717,
                                 "longitude":-46.67500346,
                                 "driverId":5997,
                                 "driverAvailable":true
                               }
    */

    public DriverPositionDTO(){}

    public DriverPositionDTO(double latitude, double longitude, Boolean driverAvailable){
        this.latitude = latitude;
        this.longitude = longitude;
        this.driverAvailable = driverAvailable;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Boolean getDriverAvailable() {
        return driverAvailable;
    }

    public void setDriverAvailable(Boolean driverAvailable) {
        this.driverAvailable = driverAvailable;
    }

    public DriverPosition toEntity(String driverId){
        DriverPosition position = new DriverPosition(latitude, longitude);
        position.setDriverId(driverId);
        position.setDate(Instant.now().toDate());
        return position;
    }

    public static  DriverPositionDTO buildDriverNotFound(String driverId){
        DriverPositionDTO dto = new DriverPositionDTO();
        dto.setDriverId("The driverId "+driverId+" was not found! Please, verify it and try again.");
        return dto;
    }

    public static DriverPositionDTO buildDriverCurrentPositionNotFound(DriverPositionDTO dto){
        dto.setDriverId("The driverId "+dto.getDriverId()+" was found! But he has no position yet! Please, set a driver position first!");
        return dto;
    }

}
