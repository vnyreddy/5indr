package com.wizdem.vinay.a5indr.models;

/**
 * Created by vinay_1 on 12/24/2016.
 */

public class SaveLocation {
    public String coordinates;
    public String message;

    public SaveLocation(){

    }
    public SaveLocation(String coordinates,String message){
        this.coordinates = coordinates;
        this.message = message;
    }
}
