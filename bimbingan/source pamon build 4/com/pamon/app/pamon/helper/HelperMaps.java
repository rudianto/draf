package com.pamon.app.pamon.helper;


import android.app.FragmentManager;
import android.content.Context;





import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

/**
 * Created by Sofiyanudin on 19/11/2017.
 */

public class HelperMaps implements OnMapReadyCallback {


    HelperCallbackMaps helperCallbackMaps;


    private GoogleMap mGoogleMap;

    private Context context;

    public HelperMaps(Context context, FragmentManager fm, int id) {
        this.context = context;
        MapFragment mapFragment = (MapFragment) fm.findFragmentById(id);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {


        mGoogleMap = googleMap;

        if(helperCallbackMaps!=null){
            helperCallbackMaps.onMapReady(googleMap);
        }

    }



    public interface HelperCallbackMaps{
        void onMapReady(GoogleMap googleMap);
    }




    public void setMapsResponse(HelperCallbackMaps helperCallbackMaps){
        this.helperCallbackMaps = helperCallbackMaps;
    }
}
