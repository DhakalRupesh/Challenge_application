package com.rupesh.baji.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
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
import com.rupesh.baji.activities.Bottom_nav;
import com.rupesh.baji.helper.ChallengeDialog;
import com.rupesh.baji.helper.StoreDialog;
import com.rupesh.baji.model.User;

public class Store extends Fragment {

    RelativeLayout rl_buypoints1_bestValue, rl_buypoints4_heavyvalue;
    LinearLayout ll_buy2_popular1, ll_buy3_popular2;
    TextView bestvalue_bp, heavyvalue_bp, tv_popular1, tv_popular2;

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
        tv_popular1 = view.findViewById(R.id.tv_popular1);
        tv_popular2 = view.findViewById(R.id.tv_popular2);

        rl_buypoints1_bestValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("bpToadd", bestvalue_bp.getText().toString().trim());
                args.putString("usrBP", Bottom_nav.user.getAmt());

                StoreDialog storeDialog = new StoreDialog();
                storeDialog.setArguments(args);
                storeDialog.show(((AppCompatActivity) getContext()).getSupportFragmentManager(), "challenge dialog");
            }
        });

        rl_buypoints4_heavyvalue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("bpToadd", heavyvalue_bp.getText().toString().trim());
                args.putString("usrBP", Bottom_nav.user.getAmt());

                StoreDialog storeDialog = new StoreDialog();
                storeDialog.setArguments(args);
                storeDialog.show(((AppCompatActivity) getContext()).getSupportFragmentManager(), "challenge dialog");
            }
        });

        ll_buy2_popular1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("bpToadd", tv_popular1.getText().toString().trim());
                args.putString("usrBP", Bottom_nav.user.getAmt());

                StoreDialog storeDialog = new StoreDialog();
                storeDialog.setArguments(args);
                storeDialog.show(((AppCompatActivity) getContext()).getSupportFragmentManager(), "challenge dialog");
            }
        });

        ll_buy3_popular2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("bpToadd", tv_popular2.getText().toString().trim());
                args.putString("usrBP", Bottom_nav.user.getAmt());

                StoreDialog storeDialog = new StoreDialog();
                storeDialog.setArguments(args);
                storeDialog.show(((AppCompatActivity) getContext()).getSupportFragmentManager(), "challenge dialog");
            }
        });

        return view;
    }
}
