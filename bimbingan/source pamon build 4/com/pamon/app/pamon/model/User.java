package com.pamon.app.pamon.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sofiyanudin on 25/11/2017.
 */

public class User {
    @SerializedName("id_user")
    @Expose
    private int idUser;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("fcm_token_orangtua")
    @Expose
    private String fcmTokenOrangtua;
    @SerializedName("fcm_token_anak")
    @Expose
    private String fcmTokenAnak;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFcmTokenOrangtua() {
        return fcmTokenOrangtua;
    }

    public void setFcmTokenOrangtua(String fcmTokenOrangtua) {
        this.fcmTokenOrangtua = fcmTokenOrangtua;
    }

    public String getFcmTokenAnak() {
        return fcmTokenAnak;
    }

    public void setFcmTokenAnak(String fcmTokenAnak) {
        this.fcmTokenAnak = fcmTokenAnak;
    }
}
