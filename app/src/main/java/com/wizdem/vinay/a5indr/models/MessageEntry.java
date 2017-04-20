package com.wizdem.vinay.a5indr.models;

/**
 * Created by vinay_1 on 4/20/2017.
 */

public class MessageEntry {
    public String uid;
    public String message;
    public String time_stamp;
    public double latitude;
    public double longitude;

    public MessageEntry(){

    }

    public MessageEntry(String uid, String message, String time_stamp, double latitude, double longitude){
        this.uid = uid;
        this.message = message;
        this.time_stamp = time_stamp;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
