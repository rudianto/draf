package com.pamon.app.pamon.network;



import com.pamon.app.pamon.model.History;
import com.pamon.app.pamon.model.Kontak;
import com.pamon.app.pamon.model.Lokasi;
import com.pamon.app.pamon.model.ResponseStatusCode;
import com.pamon.app.pamon.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by Sofiyanudin on 29/10/2017.
 */

public interface ApiNetworkInterface {
    @POST("/api/login")
    @FormUrlEncoded
    Call<ResponseStatusCode> serviceLogin(@Field("email") String email, @Field("password") String password);


    @GET("/api/user_by_email")
    Call<User> serviceGetUserByEmail(@Query("email") String email);


    @POST("/api/kontak")
    @FormUrlEncoded
    Call<ResponseStatusCode> servicePutKontak(@Field("id_user") int id_user, @Field("data") String data);


    @POST("/api/history")
    @FormUrlEncoded
    Call<ResponseStatusCode> servicePutHistory(@Field("id_user") int id_user, @Field("data") String data);

    @GET("/api/kontak")
    Call<List<Kontak>> serviceGetKontak(@Query("id_user") int id_user);

    @GET("/api/history")
    Call<List<History>> serviceGetHistory(@Query("id_user") int id_user);

    @PUT("/api/lokasi")
    @FormUrlEncoded
    Call<ResponseStatusCode> servicePutLokasi(@Field("id_user") int id_user,
                                              @Field("lat") double lat,
                                              @Field("lng") double lng);
    @GET("/api/lokasi")
    Call<Lokasi> serviceGetLokasi(@Query("id_user") int id_user);


}
