package com.pamon.app.pamon.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pamon.app.pamon.R;
import com.pamon.app.pamon.adapter.AdapterRecyclerView;
import com.pamon.app.pamon.helper.HelperMan;
import com.pamon.app.pamon.model.CardRecyclerView;
import com.pamon.app.pamon.model.History;
import com.pamon.app.pamon.model.Kontak;
import com.pamon.app.pamon.network.ApiNetwork;
import com.pamon.app.pamon.session.SessionUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sofiyanudin on 21/11/2017.
 */

public class FragmentPrivateC extends Fragment {

    List<CardRecyclerView> listCard;
    AdapterRecyclerView adapterRecyclerView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public FragmentPrivateC() {
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
        View view =  inflater.inflate(R.layout.fragment_private_c, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        listCard = new ArrayList<CardRecyclerView>();
        adapterRecyclerView = new AdapterRecyclerView(getActivity(),listCard);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        GridLayoutManager glm = new GridLayoutManager(getActivity(),2);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(llm);

        recyclerView.setAdapter(adapterRecyclerView);

        adapterRecyclerView.notifyDataSetChanged();

        adapterRecyclerView.setOnItemClickListener(new AdapterRecyclerView.OnItemClickListener() {
            @Override
            public void onItemPosition(int position) {
                dialPhoneNumber(listCard.get(position).getContent());
            }
        });
        setup(new SessionUser(getActivity()).getIdUser());
        return view;
    }

    private void setup(int id_user) {
        if (listCard.size() > 0) {
            listCard.clear();
        }


        ApiNetwork apiNetwork = new ApiNetwork(getActivity());
        apiNetwork.getKontak(id_user, new ApiNetwork.OnReponseKontakList() {
            @Override
            public void onResponse(List<Kontak> kontakList) {
                for(int i=0;i<kontakList.size();i++){

                    CardRecyclerView card = new CardRecyclerView();
                    card.setId(kontakList.get(i).getIdKontak());

                    card.setTitle(HelperMan.urlDecoder(kontakList.get(i).getNama()));
                    card.setContent(HelperMan.urlDecoder(kontakList.get(i).getNoTelepon()));

                    card.setLayout(CardRecyclerView.LAYOUT_B);
                    listCard.add(card);

                }
                adapterRecyclerView.notifyDataSetChanged();
            }

            @Override
            public void onFailure() {

            }
        });

    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
