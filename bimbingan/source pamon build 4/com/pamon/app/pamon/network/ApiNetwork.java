package com.pamon.app.pamon.network;


import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


import com.pamon.app.pamon.R;
import com.pamon.app.pamon.dialog.DialogProgress;
import com.pamon.app.pamon.model.History;
import com.pamon.app.pamon.model.Kontak;
import com.pamon.app.pamon.model.Lokasi;
import com.pamon.app.pamon.model.ResponseStatusCode;
import com.pamon.app.pamon.model.User;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiNetwork {
    public final static String BASE_URL =  "http://api.pamonapp.com";


    private static Retrofit retrofit = null;

    public static ApiNetwork mInstance;
    private static Context mContext;

     DialogProgress dialogProgress;
    public ApiNetwork(Context context){
        mContext = context;

        dialogProgress = new DialogProgress(mContext);


    }



    private Retrofit getClient() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request.Builder builder = originalRequest.newBuilder();

                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())

                .build();

        return retrofit;
    }

    public interface OnReponseStatusCode{
        void onResponse(ResponseStatusCode statusCode);
        void onFailure();
    }


    public interface OnReponseUser{
        void onResponse(User user);
        void onFailure();
    }

    public interface OnReponseHistoryList{
        void onResponse(List<History> historyList);
        void onFailure();
    }

    public interface OnReponseLokasi{
        void onResponse(Lokasi lokasi);
        void onFailure();
    }

    public interface OnReponseKontakList{
        void onResponse(List<Kontak> kontakList);
        void onFailure();
    }
    public void postLogin(String email, String password, final OnReponseStatusCode onReponse){

        dialogProgress.show();


        ApiNetworkInterface serviceNetwork = getClient().create(ApiNetworkInterface.class);

        Call<ResponseStatusCode> loginCall = serviceNetwork.serviceLogin(email, password);
        loginCall.enqueue(new Callback<ResponseStatusCode>() {
            @Override
            public void onResponse(Call<ResponseStatusCode> call, Response<ResponseStatusCode> response) {
                if(onReponse != null){
                    onReponse.onResponse(response.body());
                    dialogProgress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseStatusCode> call, Throwable t) {
                if(onReponse != null) onReponse.onFailure();
                dialogProgress.dismiss();
            }
        });

    }


    public void putKontak(int id_user, String data,final OnReponseStatusCode onResponse){
        ApiNetworkInterface serviceNetwork = getClient().create(ApiNetworkInterface.class);

        Call<ResponseStatusCode> call = serviceNetwork.servicePutKontak(id_user, data);
        call.enqueue(new Callback<ResponseStatusCode>() {
            @Override
            public void onResponse(Call<ResponseStatusCode> call, Response<ResponseStatusCode> response) {
                if(onResponse != null){
                    onResponse.onResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseStatusCode> call, Throwable t) {
                if(onResponse != null) onResponse.onFailure();
                dialogProgress.dismiss();
                Log.i("ERROR_NETWORK",t.getMessage());
            }
        });
    }

    public void putHistory(int id_user, String data, final OnReponseStatusCode onResponse){
        ApiNetworkInterface serviceNetwork = getClient().create(ApiNetworkInterface.class);

        Call<ResponseStatusCode> call = serviceNetwork.servicePutHistory(id_user, data);
        call.enqueue(new Callback<ResponseStatusCode>() {
            @Override
            public void onResponse(Call<ResponseStatusCode> call, Response<ResponseStatusCode> response) {
                if(onResponse != null){
                    onResponse.onResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseStatusCode> call, Throwable t) {
                if(onResponse != null) onResponse.onFailure();
                dialogProgress.dismiss();
                Log.i("ERROR_NETWORK",t.getMessage());
            }
        });
    }
    public void getUserByEmail(String email,final OnReponseUser onResponse){
        dialogProgress.show();
        ApiNetworkInterface serviceNetwork = getClient().create(ApiNetworkInterface.class);

        Call<User> driverCall = serviceNetwork.serviceGetUserByEmail(email);
        driverCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(onResponse != null){
                    onResponse.onResponse(response.body());
                    dialogProgress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                if(onResponse != null) onResponse.onFailure();
                dialogProgress.dismiss();
                Log.i("ERROR_NETWORK",t.getMessage());

            }
        });

    }










    public void getHistory(int id_user, final OnReponseHistoryList onResponse){
        dialogProgress.show();

        ApiNetworkInterface serviceNetwork = getClient().create(ApiNetworkInterface.class);

        Call<List<History>> call = serviceNetwork.serviceGetHistory(id_user);
        call.enqueue(new Callback<List<History>>() {
            @Override
            public void onResponse(Call<List<History>> call, Response<List<History>> response) {
                if(onResponse != null){
                    onResponse.onResponse(response.body());
                    dialogProgress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<History>> call, Throwable t) {
                if(onResponse != null) onResponse.onFailure();
                dialogProgress.dismiss();
                Log.i("ERROR_NETWORK",t.getMessage());
            }
        });

    }


    public void getKontak(int id_user, final OnReponseKontakList onResponse){
        dialogProgress.show();

        ApiNetworkInterface serviceNetwork = getClient().create(ApiNetworkInterface.class);

        Call<List<Kontak>> call = serviceNetwork.serviceGetKontak(id_user);
        call.enqueue(new Callback<List<Kontak>>() {
            @Override
            public void onResponse(Call<List<Kontak>> call, Response<List<Kontak>> response) {
                if(onResponse != null){
                    onResponse.onResponse(response.body());
                    dialogProgress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<Kontak>> call, Throwable t) {
                if(onResponse != null) onResponse.onFailure();
                dialogProgress.dismiss();
                Log.i("ERROR_NETWORK",t.getMessage());
            }
        });


    }


    public void getLokasi(int id_user,final OnReponseLokasi onResponse){
        ApiNetworkInterface serviceNetwork = getClient().create(ApiNetworkInterface.class);

        Call<Lokasi> call = serviceNetwork.serviceGetLokasi(id_user);
        call.enqueue(new Callback<Lokasi>() {
            @Override
            public void onResponse(Call<Lokasi> call, Response<Lokasi> response) {
                if(onResponse != null){
                    onResponse.onResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<Lokasi> call, Throwable t) {

            }
        });
    }

    public void putLokasi(int id_user, double lat, double lng, final OnReponseStatusCode onResponse){
        ApiNetworkInterface serviceNetwork = getClient().create(ApiNetworkInterface.class);

        Call<ResponseStatusCode> call = serviceNetwork.servicePutLokasi(id_user, lat, lng);
        call.enqueue(new Callback<ResponseStatusCode>() {
            @Override
            public void onResponse(Call<ResponseStatusCode> call, Response<ResponseStatusCode> response) {
                if(onResponse != null){
                    onResponse.onResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseStatusCode> call, Throwable t) {
                if(onResponse != null) onResponse.onFailure();
                dialogProgress.dismiss();
                Log.i("ERROR_NETWORK",t.getMessage());
            }
        });
    }
    public View getLoader(Activity activity){

        View vLoad = activity.getLayoutInflater().inflate(R.layout.progress_dialog_bottom,null);
        activity.addContentView(vLoad,new DrawerLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return vLoad;

    }

}
