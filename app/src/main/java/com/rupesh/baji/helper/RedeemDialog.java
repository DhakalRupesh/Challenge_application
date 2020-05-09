package com.rupesh.baji.helper;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.rupesh.baji.R;
import com.rupesh.baji.activities.Bottom_nav;
import com.rupesh.baji.activities.ProfileEdit;
import com.rupesh.baji.api.Useri;
import com.rupesh.baji.model.User;
import com.rupesh.baji.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RedeemDialog extends AppCompatDialogFragment {

    EditText etRedeemPoint, etCardNumber, etCurrentLocation;
    TextView tvBPpoints, tvRedeemConvertPoint;
    Button btnRedeem;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.redeem_dialog, null);

        builder.setView(view)
        .setTitle("Redeem using Pay Pal")
        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        etRedeemPoint = view.findViewById(R.id.etRedeemPoints);
        etCardNumber = view.findViewById(R.id.et_redeem_CardNumber);
        etCurrentLocation = view.findViewById(R.id.et_redeem_location);

        tvRedeemConvertPoint = view.findViewById(R.id.tv_redeem_pointConvertedtv);
        tvBPpoints = view.findViewById(R.id.tv_redeem_bp);

        btnRedeem = view.findViewById(R.id.btn_redeem);

        tvBPpoints.setText(Bottom_nav.user.getAmt());

        etRedeemPoint.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    int converted = Integer.parseInt(etRedeemPoint.getText().toString())/2;
                    String finalAmount = Integer.toString(converted);
                    tvRedeemConvertPoint.setText(finalAmount);
                } catch (NumberFormatException ex){

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnRedeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number  = Integer.parseInt(Bottom_nav.user.getAmt());
                try{
                    if(number < Integer.parseInt(etRedeemPoint.getText().toString())){
                        etRedeemPoint.setError("You don't have " + etRedeemPoint.getText().toString() + " points to redeem");
                        return;
                    }if(Integer.parseInt(etRedeemPoint.getText().toString()) == 0) {
                        etRedeemPoint.setError("You can't redeem 0 points");
                        return;
                    }

                    try {
                        int userPoint  = Integer.parseInt(Bottom_nav.user.getAmt());
                        String fname = Bottom_nav.user.getFname();
                        if(Integer.parseInt(etRedeemPoint.getText().toString()) <= userPoint){
                            int x = userPoint - Integer.parseInt(etRedeemPoint.getText().toString());
                            String remainBp = Integer.toString(x);

                            User updateBp = new User(fname, remainBp);

                            Useri useri = Url.getInstance().create(Useri.class);
                            Call<User> updateCall = useri.updateProfile(Url.token, updateBp);

                            updateCall.enqueue(new Callback<User>() {
                                @Override
                                public void onResponse(Call<User> call, Response<User> response) {

                                    if (!response.isSuccessful()) {
                                        Toast.makeText(getContext(), "Error !!", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    Toast.makeText(getContext(), "Successfully redeemed", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<User> call, Throwable t) {
                                    Toast.makeText(getContext(), "Error!! " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    } catch (NumberFormatException ex) {

                    }

                } catch (NumberFormatException ex){

                }
            }
        });

        return builder.create();
    }
}
