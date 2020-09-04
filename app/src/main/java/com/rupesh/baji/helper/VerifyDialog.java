package com.rupesh.baji.helper;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.rupesh.baji.R;
import com.rupesh.baji.activities.Bottom_nav;
import com.rupesh.baji.activities.ChallengeDetail;
import com.rupesh.baji.activities.ChallengerProfile;
import com.rupesh.baji.api.Challengei;
import com.rupesh.baji.api.Useri;
import com.rupesh.baji.model.Challenge;
import com.rupesh.baji.model.User;
import com.rupesh.baji.url.Url;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class VerifyDialog extends AppCompatDialogFragment {

    Button btn_verify;
    TextView tvVerificationUsr, verifyusrBp;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.resverify_dialog, null);

        builder.setView(view);

        btn_verify = view.findViewById(R.id.btn_resverify_confirm);
        tvVerificationUsr = view.findViewById(R.id.verificationsenduser);
        verifyusrBp = view.findViewById(R.id.verifyusrBp);

        Bundle mArgs = getArguments();
        String sendBy = mArgs.getString("verificationSendBy");
        String senderBp = mArgs.getString("verifiersBP");
        String wonBp = mArgs.getString("wonBp");
        String Fname = mArgs.getString("fname");
        String ToVerifychID = mArgs.getString("ToVerifychID");
        String chType = mArgs.getString("chType");

        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int currentuserPoint  = Integer.parseInt(Bottom_nav.user.getAmt());
                    String fname = Bottom_nav.user.getFname();
                    int userPoint = Integer.parseInt(senderBp);
                    int wonPoints = Integer.parseInt(wonBp);
                    int upsPoints = userPoint + wonPoints * 2;

                    String finalPoint = Integer.toString(upsPoints);
                    // updating winning user status
                    User userUpdate = new User(Fname, finalPoint);

                    Useri useri = Url.getInstance().create(Useri.class);
                    Call<Void> voidCallUser = useri.updateBpStatus(sendBy, userUpdate);

                    voidCallUser.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(getContext(), "Code " + response.code(), Toast.LENGTH_SHORT).show();
                                return;
                            }

                            Toast.makeText(getContext(), "verified", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getContext(), "Error!! " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    // challenge verification update
                    Challenge updateChallenge = new Challenge(chType, "verified");

                    Challengei challengei = Url.getInstance().create(Challengei.class);
                    Call<Void> challengeVoidCall = challengei.updateChallengeStatus(ToVerifychID, updateChallenge);

                    challengeVoidCall.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(getContext(), "Code " + response.code(), Toast.LENGTH_SHORT).show();
                                return;
                            }

                            Toast.makeText(getContext(), "verified", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getContext(), com.rupesh.baji.activities.Challenge.class));
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getContext(), "Error!! " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


                } catch (NumberFormatException ex) {

                }
            }
        });

        return builder.create();
    }
}
