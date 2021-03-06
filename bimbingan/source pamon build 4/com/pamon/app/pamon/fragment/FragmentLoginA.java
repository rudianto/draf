package com.pamon.app.pamon.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.pamon.app.pamon.ActivityLogin;
import com.pamon.app.pamon.ActivityMain;
import com.pamon.app.pamon.R;

import com.pamon.app.pamon.helper.HelperMan;
import com.pamon.app.pamon.model.ResponseStatusCode;
import com.pamon.app.pamon.model.User;
import com.pamon.app.pamon.network.ApiNetwork;
import com.pamon.app.pamon.session.SessionUser;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentLoginA#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentLoginA extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public FragmentLoginA() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentLoginA.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentLoginA newInstance(String param1, String param2) {
        FragmentLoginA fragment = new FragmentLoginA();
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
        final View view=  inflater.inflate(R.layout.fragment_login_a, container, false);

        view.findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                if(HelperMan.checkGPS(getActivity())){
                    final String email, password;
                    email = ((EditText) view.findViewById(R.id.textEmail)).getText().toString();
                    password = ((EditText) view.findViewById(R.id.textPassword)).getText().toString();

                    ApiNetwork apiNetwork = new ApiNetwork(getActivity());
                    apiNetwork.postLogin(email, password, new ApiNetwork.OnReponseStatusCode() {
                        @Override
                        public void onResponse(ResponseStatusCode statusCode) {
                            if(statusCode.check()){
                                setup(email);
                            }else{
                                HelperMan.message("Email dan password yang anda gunakan salah!",v.getContext());
                            }
                        }

                        @Override
                        public void onFailure() {

                        }
                    });
                }else{
                    AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

                    alert.setMessage("Akses lokasi tidak tersedia, silahkan aktifkan lokasi terlebih dahulu.");
                    alert.setPositiveButton("Aktifkan", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivityForResult(intent, 10);
                        }
                    });
                    alert.show();
                }

            }
        });

        return view;
    }

    private void setup(String email){
        ApiNetwork apiNetwork = new ApiNetwork(getActivity());
        apiNetwork.getUserByEmail(email, new ApiNetwork.OnReponseUser() {
            @Override
            public void onResponse(User user) {
                new SessionUser(getActivity()).login(user.getIdUser(),user.getNama(),0);
                Intent i = new Intent(getActivity(),ActivityMain.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(i);
                ((ActivityLogin) getActivity()).finish();
            }

            @Override
            public void onFailure() {

            }
        });

    }

}
