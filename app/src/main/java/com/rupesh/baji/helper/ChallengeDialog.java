package com.rupesh.baji.helper;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.gson.Gson;
import com.rupesh.baji.R;
import com.rupesh.baji.model.Result;
import com.rupesh.baji.model.User;

public class ChallengeDialog extends AppCompatDialogFragment {

    private RadioGroup rdoBtnGrp;
    private RadioButton rdoBtnWonBy;
    ImageView imgProofing;

    TextView tvChid, tvAccepted, tvChallenger;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.challenge_dialouge, null);

        Bundle mArgs = getArguments();
        String challengeID = mArgs.getString("chID");
        String acceptedByid = mArgs.getString("acceptedBY");
        String challenger = mArgs.getString("challenger");

        Gson gson = new Gson();
        

        builder.setView(view)
                .setTitle("Post Result")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Post", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        rdoBtnGrp = view.findViewById(R.id.rdogrp_result);
        imgProofing = view.findViewById(R.id.img_res_proof_image);
        tvChid = view.findViewById(R.id.tv_res_chID);
        tvAccepted = view.findViewById(R.id.tv_res_acceptedbyID);
        tvChallenger = view.findViewById(R.id.tv_res_challenger);

        tvChid.setText("challengeID: " + challengeID);
        tvAccepted.setText("acceptedBy: " + acceptedByid);
        tvChallenger.setText("challenger: " + challenger);

        return builder.create();
    }

}
