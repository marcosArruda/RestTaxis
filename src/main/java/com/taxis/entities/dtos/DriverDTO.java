package com.taxis.entities.dtos;

import java.io.Serializable;

/**
 * Created by marcosarruda on 2/15/16.
 */
public class DriverDTO implements Serializable{
    private String name;
    private String carPlate;

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
}
