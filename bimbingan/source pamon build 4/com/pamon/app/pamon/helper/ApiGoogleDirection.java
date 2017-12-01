package com.pamon.app.pamon.helper;


import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiGoogleDirection {
    ApiGoogleDirectionOnResponse apiGoogleDirectionOnResponse;
    Retrofit retrofit;
    private Retrofit getClient(String url) {

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
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())

                .build();

        return retrofit;
    }


    interface ApiGoogleDirectionInterace{
        @GET("/maps/api/directions/json")
        Call<ResponseBody> serviceResponse(@Query("origin") String origin,
                                           @Query("destination") String destination,
                                           @Query("waypoints") String waypoints,
                                           @Query("mode") String mode,
                                           @Query("key") String key);
    }
    public interface ApiGoogleDirectionOnResponse{
        void onReponse(List<LatLng> list);
    }

    public interface ApiGoogleDirectionOnResponseDistance{
        void onReponse(double distance);
    }
    public void OK(LatLng a, LatLng b,List<LatLng> waypoints, final ApiGoogleDirectionOnResponse apiGoogleDirectionOnResponse){

        Log.i("SSS",listToWaypointsUrl(waypoints));
        String start = a.latitude+","+a.longitude;
        String finish = b.latitude+","+b.longitude;
        ApiGoogleDirectionInterace api = getClient("https://maps.googleapis.com/").create(ApiGoogleDirectionInterace.class);

        Call<ResponseBody> responseBodyCall = api.serviceResponse(start,finish,listToWaypointsUrl(waypoints),"driving","");
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    JSONObject jsonObject = new JSONObject(response.body().string());
                    JSONArray routeArray = jsonObject.getJSONArray("routes");
                    JSONObject routes = routeArray.getJSONObject(0);
                    JSONObject overviewPolylines = routes.getJSONObject("overview_polyline");
                    String encodedString = overviewPolylines.getString("points");
                    List<LatLng> list = decodePoly(encodedString);

                    if(apiGoogleDirectionOnResponse != null) apiGoogleDirectionOnResponse.onReponse(list);

                } catch (IOException e) {

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    public void OKDISTANCE(LatLng a, LatLng b,List<LatLng> waypoints, final ApiGoogleDirectionOnResponseDistance onReponse){

        String start = a.latitude+","+a.longitude;
        String finish = b.latitude+","+b.longitude;
        ApiGoogleDirectionInterace api = getClient("https://maps.googleapis.com/").create(ApiGoogleDirectionInterace.class);

        Call<ResponseBody> responseBodyCall = api.serviceResponse(start,finish,listToWaypointsUrl(waypoints),"driving","");
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    JSONObject jsonObject = new JSONObject(response.body().string());
                    JSONArray routeArray = jsonObject.getJSONArray("routes");
                    JSONObject routes = routeArray.getJSONObject(0);

                    JSONArray legs = routes.getJSONArray("legs");

                    JSONObject steps = legs.getJSONObject(0);

                    JSONObject distance = steps.getJSONObject("distance");

                    if(onReponse != null) onReponse.onReponse(distance.getDouble("value"));

                } catch (IOException e) {

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    public List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng( (((double) lat / 1E5)),
                    (((double) lng / 1E5) ));
            poly.add(p);
        }

        return poly;
    }

    public String listToWaypointsUrl(List<LatLng> list ){
        if(list== null) new ArrayList<LatLng>();
        StringBuilder str = new StringBuilder();
        if(list.size() >0){
            for (int i=0;i<list.size();i++){
                str.append(list.get(i).latitude);
                str.append(",");
                str.append(list.get(i).longitude);
                if(i != list.size()-1) str.append("|");
            }
        }
        return str.toString();
    }
}
