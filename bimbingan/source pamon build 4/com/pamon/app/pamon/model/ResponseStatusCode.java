package com.pamon.app.pamon.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sofiyanudin on 05/11/2017.
 */

public class ResponseStatusCode {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("code")
    @Expose
    private Integer code;

    public String getStatus() {
        return status;
    }

    public Integer getCode() {
        return code;
    }

    public boolean check(){
        if(getStatus().contentEquals("success") || getStatus().contentEquals("ok")){
            return true;
        }else return false;

    }

}
