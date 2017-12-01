package com.pamon.app.pamon.fragment;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pamon.app.pamon.R;
import com.pamon.app.pamon.helper.HelperMaps;
import com.pamon.app.pamon.model.Lokasi;
import com.pamon.app.pamon.network.ApiNetwork;
import com.pamon.app.pamon.session.SessionUser;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Sofiyanudin on 21/11/2017.
 */

public class FragmentPrivateD extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static final int DELAY = 10000;
    private Timer mTimer = null;
    private Handler mHandler;
    public FragmentPrivateD() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPrivateB.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPrivateB newInstance(String param1, String param2) {
        FragmentPrivateB fragment = new FragmentPrivateB();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_private_d, container, false);
        setupMaps();
        mHandler= new Handler();
        return view;
    }

    LatLng latLngWalk;
    private Marker mMarkerWalk;
    private GoogleMap mGoogleMap;
    public void setupMaps(){
        HelperMaps helperMaps = new HelperMaps(getActivity(),getActivity().getFragmentManager(),R.id.fragment_maps);
        helperMaps.setMapsResponse(new HelperMaps.HelperCallbackMaps() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mGoogleMap = googleMap;
                ApiNetwork apiNetwork = new ApiNetwork(getActivity());
                apiNetwork.getLokasi(new SessionUser(getActivity()).getIdUser(), new ApiNetwork.OnReponseLokasi() {
                    @Override
                    public void onResponse(Lokasi lokasi) {
                        if(mGoogleMap != null){
                            if (mMarkerWalk != null)
                                mMarkerWalk.remove();
                            LatLng latLng = new LatLng(lokasi.getLat(), lokasi.getLng());

                            MarkerOptions markerOptions = new MarkerOptions()
                                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_anak))
                                    .position(latLng)
                                    .title("Track (ANAK)")
                                    //.title(lokasiDriver.getNamaDriver() + "\n#"+lokasiDriver.getLat()+ "\n#"+lokasiDriver.getLng())
                                    .anchor(0.1f,0.5f)
                                    .flat(true);
                            mMarkerWalk =  mGoogleMap.addMarker(markerOptions);


                            CameraPosition cameraPosition = new CameraPosition.Builder()
                                    .target(latLng)      // Sets the center of the map to Mountain View
                                    .zoom(17)                   // Sets the zoom
                                    .bearing(0)                // Sets the orientation of the camera to east
                                    .tilt(0)                   // Sets the tilt of the camera to 30 degrees
                                    .build();                   // Creates a CameraPosition from the builder

                            mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                        }
                    }

                    @Override
                    public void onFailure() {

                    }
                });
//
//                if (mTimer != null) // Cancel if already existed
//                    mTimer.cancel();
//                else
//                    mTimer = new Timer();   //recreate new
//                mTimer.scheduleAtFixedRate(new TimeDisplay(), 0, DELAY);   //Schedule task

            }
        });
    }
    class TimeDisplay extends TimerTask {
        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {
                @Override
                public void run() {

                }
            });

        }

    }

}
