package com.wizdem.vinay.a5indr.models;

import java.io.Serializable;

/**
 * Created by vinay_1 on 10/16/2016.
 */

public class Users {
    public String username;
    public String email;

    public Users (){

    }
    public Users(String username,String email){
        this.username = username;
        this.email = email;
    }

}
