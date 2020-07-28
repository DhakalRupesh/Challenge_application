package com.rupesh.baji.helper;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.gson.Gson;
import com.rupesh.baji.R;
import com.rupesh.baji.activities.Bottom_nav;
import com.rupesh.baji.api.Challengei;
import com.rupesh.baji.api.Resulti;
import com.rupesh.baji.fragments.CurrentChallenges;
import com.rupesh.baji.model.Challenge;
import com.rupesh.baji.model.Result;
import com.rupesh.baji.model.User;
import com.rupesh.baji.url.Url;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChallengeDialog extends AppCompatDialogFragment {

    private RadioGroup rdoBtnGrp, rdoBtnGrp2;
    private RadioButton rdoBtnWonByMe, rdoBtnWonByOpp;
    ImageView imgProofing;

    TextView tvChid, tvAccepted, tvChallenger;
    Spinner spinner;
    ArrayList<String> list = new ArrayList<>();
//    private String WonBY;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.challenge_dialouge, null);

        Bundle mArgs = getArguments();
        String challengeID = mArgs.getString("chID");
        String acceptedByid = mArgs.getString("acceptedBY");
        String challengerID = mArgs.getString("challenger");

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
                        PostResult();
                    }
                });

//        rdoBtnGrp = view.findViewById(R.id.rdogrp_result);
        imgProofing = view.findViewById(R.id.img_res_proof_image);
        tvChid = view.findViewById(R.id.tv_res_chID);
        tvAccepted = view.findViewById(R.id.tv_res_acceptedbyID);
        tvChallenger = view.findViewById(R.id.tv_res_challenger);

        tvChid.setText("challengeID: " + challengeID);
        tvAccepted.setText("acceptedBy: " + acceptedByid);
        tvChallenger.setText("challenger: " + challengerID);

        return builder.create();
    }

    public void PostResult() {

        Bundle mArgs = getArguments();

        User wonBy  = new User(Bottom_nav.user.get_id());
        Challenge ChallengeWon = new Challenge(mArgs.getString("chID"));
//        User ChBy = new User(mArgs.getString("challenger"));
        User chacceptedBy = new User(mArgs.getString("acceptedBY"));
        User confirmationSendBy = new User(Bottom_nav.user.get_id());

        String confirmationType = "waiting";
        String imageProofing = "image goes here";

//        Result postResult = new Result(
//                wonBy, ChallengeWon , chacceptedBy, confirmationSendBy, confirmationType, imageProofing
//        );

//        Resulti myResultAPI = Url.getInstance().create(Resulti.class);
//        Call<Void> myResultCall = myResultAPI.submitResult(postResult);

        Challenge updateChallenge = new Challenge(wonBy, confirmationSendBy, confirmationType, imageProofing);
        Bundle mArgss = getArguments();
        String challengeId = mArgss.getString("chID");

        Challengei challengeiUpdate = Url.getInstance().create(Challengei.class);
        Call<Void> myChallengeCall = challengeiUpdate.updateResVerification(challengeId, updateChallenge);

        myChallengeCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getContext(), "Result verification send", Toast.LENGTH_SHORT).show();
                return;
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        });

//        myResultCall.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                if (!response.isSuccessful()) {
//                    Toast.makeText(getContext(), "Error !!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                Toast.makeText(getContext(), "Result verification send", Toast.LENGTH_SHORT).show();
//
//                getActivity().finish();
//
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//                Toast.makeText(getContext(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                return;
//            }
//        });

    }

}
