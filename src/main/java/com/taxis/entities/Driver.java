package com.taxis.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * Created by marcosarruda on 2/13/16.
 */


@Document(collection="drivers")
public class Driver implements Serializable{
    @Id
    private String driverId;
    private String name;
    private String carPlate;
    private Boolean driverAvailable;
    private DriverPosition currentPosition;
    private List<DriverPosition> positions;

    public Driver(){}
    public Driver(String name, String carPlate){
        this.name = name;
        this.carPlate = carPlate;
    }
    public DriverPosition getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(DriverPosition currentPosition) {
        this.currentPosition = currentPosition;
    }

    public List<DriverPosition> getPositions() {
        return positions;
    }

    public void setPositions(List<DriverPosition> positions) {
        this.positions = positions;
    }



    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
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
        return this.driverAvailable;
    }

    public void setDriverAvailable(Boolean driverAvailable) {
        this.driverAvailable = driverAvailable;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Driver driver = (Driver) o;

        if (getDriverId() != null ? !getDriverId().equals(driver.getDriverId()) : driver.getDriverId() != null)
            return false;
        if (getName() != null ? !getName().equals(driver.getName()) : driver.getName() != null) return false;
        if (getCarPlate() != null ? !getCarPlate().equals(driver.getCarPlate()) : driver.getCarPlate() != null)
            return false;
        return !(getDriverAvailable() != null ? !getDriverAvailable().equals(driver.getDriverAvailable()) : driver.getDriverAvailable() != null);

    }

    @Override
    public int hashCode() {
        int result = getDriverId() != null ? getDriverId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getCarPlate() != null ? getCarPlate().hashCode() : 0);
        result = 31 * result + (getDriverAvailable() != null ? getDriverAvailable().hashCode() : 0);
        return result;
    }
}
