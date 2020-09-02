package com.rupesh.baji.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.khalti.widget.KhaltiButton;
import com.rupesh.baji.R;

public class Store extends Fragment {

    RelativeLayout rl_buypoints1_bestValue, rl_buypoints4_heavyvalue;
    LinearLayout ll_buy2_popular1, ll_buy3_popular2;
    TextView bestvalue_bp, heavyvalue_bp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store, container, false);


        rl_buypoints1_bestValue = view.findViewById(R.id.rl_buypoints1_bestValue);
        rl_buypoints4_heavyvalue = view.findViewById(R.id.rl_buypoints4_heavyvalue);

        ll_buy2_popular1 = view.findViewById(R.id.ll_buy2_popular1);
        ll_buy3_popular2 = view.findViewById(R.id.ll_buy3_popular2);

        bestvalue_bp = view.findViewById(R.id.bestvalue_bp);
        heavyvalue_bp = view.findViewById(R.id.heavyvalue_bp);

        rl_buypoints1_bestValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        rl_buypoints4_heavyvalue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ll_buy2_popular1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ll_buy3_popular2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }
}
