package com.taxis.entities;

import com.google.common.base.Objects;
import org.joda.time.Instant;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by marcosarruda on 2/13/16.
 */
public class DriverPosition {

    private Date date;
    private String driverId;
    private double latitude;
    private double longitude;

    /*
    POST /drivers/8475/status '{"latitude":-
            23.60810717,"longitude":-
            46.67500346,"driverId":5997,"driverAvailable":true}'
    */

    public DriverPosition(){}

    public DriverPosition(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DriverPosition that = (DriverPosition) o;

        if (Double.compare(that.getLatitude(), getLatitude()) != 0) return false;
        if (Double.compare(that.getLongitude(), getLongitude()) != 0) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return !(getDriverId() != null ? !getDriverId().equals(that.getDriverId()) : that.getDriverId() != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = date != null ? date.hashCode() : 0;
        result = 31 * result + (getDriverId() != null ? getDriverId().hashCode() : 0);
        temp = Double.doubleToLongBits(getLatitude());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getLongitude());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("driverId", driverId)
                .add("latitude", latitude)
                .add("longitude", longitude)
                .toString();
    }
}
