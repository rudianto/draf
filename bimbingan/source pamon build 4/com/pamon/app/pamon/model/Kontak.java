package com.pamon.app.pamon.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sofiyanudin on 25/11/2017.
 */

public class Kontak {

    @SerializedName("id_kontak")
    @Expose
    private int idKontak;
    @SerializedName("id_user")
    @Expose
    private int idUser;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("no_telepon")
    @Expose
    private String noTelepon;


    public int getIdKontak() {
        return idKontak;
    }

    public void setIdKontak(int idKontak) {
        this.idKontak = idKontak;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }
}
