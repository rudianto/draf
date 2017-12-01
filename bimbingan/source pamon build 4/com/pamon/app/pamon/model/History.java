package com.pamon.app.pamon.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sofiyanudin on 25/11/2017.
 */

public class History {

    @SerializedName("id_history")
    @Expose
    private int idHistory;
    @SerializedName("id_user")
    @Expose
    private int idUser;
    @SerializedName("url")
    @Expose
    private String url;


    public int getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(int idHistory) {
        this.idHistory = idHistory;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
