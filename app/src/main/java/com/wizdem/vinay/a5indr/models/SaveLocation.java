package com.wizdem.vinay.a5indr.models;

/**
 * Created by vinay_1 on 12/24/2016.
 */

public class SaveLocation {
    public double latitude;
    public double logitude;
    public String message;

    public SaveLocation(){

    }
    public SaveLocation(String message, double longitude, double latitude) {
        this.latitude = latitude;
        this.logitude = longitude;
        this.message = message;
    }
}
