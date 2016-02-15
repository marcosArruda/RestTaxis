package com.taxis.entities;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

/**
 * Created by marcosarruda on 2/13/16.
 */


@Document(collection="drivers")
public class Driver {
    @Id
    private Long driverId;
    private String name;
    private String carPlate;
    private DriverPosition lastPosition;

    public Driver(){}
    public Driver(String name, String carPlate){
        this.name = name;
        this.carPlate = carPlate;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public Boolean getDriverAvailable() {

        return lastPosition == null? null : lastPosition.getDriverAvailable();
    }

    public void setDriverAvailable(Boolean driverAvailable) {
        if(lastPosition != null)
            this.lastPosition.setDriverAvailable(driverAvailable);
        else
            throw new RuntimeException("@> Driver's lastPosition param is not set; See com.taxis.entities.Driver#driverId="+driverId);
    }

    public DriverPosition getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(DriverPosition lastPosition) {
        if(lastPosition != null && lastPosition.getDriverId() == null){
            lastPosition.setDriverId(this.driverId);
        }
        this.lastPosition = lastPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Driver driver = (Driver) o;

        if (!getName().equals(driver.getName())) return false;
        if (!getCarPlate().equals(driver.getCarPlate())) return false;
        return !(getLastPosition() != null ? !getLastPosition().equals(driver.getLastPosition()) : driver.getLastPosition() != null);

    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getCarPlate().hashCode();
        result = 31 * result + (getLastPosition() != null ? getLastPosition().hashCode() : 0);
        return result;
    }
}
