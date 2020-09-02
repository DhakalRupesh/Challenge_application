package com.rupesh.baji.helper;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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

    Button btnSubmitResult;
    ImageView imgProofing;

    TextView tvChid, tvAccepted, tvChallenger;
    ArrayList<String> list = new ArrayList<>();

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

        builder.setView(view);
//                .setTitle("Post Result")
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                })
//                .setPositiveButton("Post", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        PostResult();
//                    }
//                });

        imgProofing = view.findViewById(R.id.img_res_proof_image);
        tvChid = view.findViewById(R.id.tv_res_chID);
        tvAccepted = view.findViewById(R.id.tv_res_acceptedbyID);
        tvChallenger = view.findViewById(R.id.tv_res_challenger);

        btnSubmitResult = view.findViewById(R.id.btn_res_post);

        tvChid.setText("challengeID: " + challengeID);
        tvAccepted.setText("acceptedBy: " + acceptedByid);
        tvChallenger.setText("challenger: " + challengerID);

        btnSubmitResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostResult();
            }
        });

        return builder.create();
    }

    public void PostResult() {
        String confirmationType = "waiting";
        String imageProofing = "image goes here";

        Bundle mArgss = getArguments();
        String challengeId = mArgss.getString("chID");
        String currentUsr = mArgss.getString("currentUsr");

        User curUsr = new User(currentUsr);

        Challenge updateChallenge = new Challenge(curUsr, curUsr, confirmationType, imageProofing);
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
                startActivity(new Intent(getContext(), com.rupesh.baji.activities.Challenge.class));
                return;
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        });

    }

}
