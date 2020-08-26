package com.rupesh.baji.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.khalti.widget.KhaltiButton;
import com.rupesh.baji.R;

public class Store extends Fragment {

    RelativeLayout rl_buypoints1, buyooints2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store, container, false);

        // esewa integration

        rl_buypoints1 = view.findViewById(R.id.rl_buypoints1);


        return view;
    }
}
