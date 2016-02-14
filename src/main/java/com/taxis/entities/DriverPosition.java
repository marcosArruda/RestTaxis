package com.taxis.entities;

import com.google.common.base.Objects;

import java.math.BigDecimal;

/**
 * Created by marcosarruda on 2/13/16.
 */
public class DriverPosition {
    private Long driverId;
    private BigDecimal lat;
    private BigDecimal lng;
    private Boolean driverAvailable;

    public DriverPosition(){}

    public DriverPosition(BigDecimal lat, BigDecimal lng, Boolean driverAvailable){
        this.lat = lat;
        this.lng = lng;
        this.driverAvailable = driverAvailable;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Boolean getDriverAvailable() {
        return driverAvailable;
    }

    public void setDriverAvailable(Boolean driverAvailable) {
        this.driverAvailable = driverAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DriverPosition that = (DriverPosition) o;

        if (!lat.equals(that.lat)) return false;
        if (!lng.equals(that.lng)) return false;
        if (!driverId.equals(that.driverId)) return false;
        return driverAvailable.equals(that.driverAvailable);

    }

    @Override
    public int hashCode() {
        int result = lat.hashCode();
        result = 31 * result + lng.hashCode();
        result = 31 * result + driverId.hashCode();
        result = 31 * result + driverAvailable.hashCode();
        return result;
    }


    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("lat", lat)
                .add("lng", lng)
                .add("driverId", driverId)
                .add("driverAvailable", driverAvailable)
                .toString();
    }

}
