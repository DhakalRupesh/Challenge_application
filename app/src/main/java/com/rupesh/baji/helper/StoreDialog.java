package com.rupesh.baji.helper;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
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
import com.rupesh.baji.api.Useri;
import com.rupesh.baji.model.User;
import com.rupesh.baji.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreDialog extends AppCompatDialogFragment {
    Button btn_submit_purchase;
    TextView tv_store_title;
    EditText et_cardno;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.store_dialog, null);

        builder.setView(view);

        et_cardno = view.findViewById(R.id.et_store_CardNumber);
        btn_submit_purchase = view.findViewById(R.id.btn_store_Purchase);
        tv_store_title = view.findViewById(R.id.tv_store_title);

        Bundle mArgs = getArguments();
        String bpToadd = mArgs.getString("bpToadd");
        String usrBP = mArgs.getString("usrBP");

        btn_submit_purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CheckEmpty()) {
                    try {
                        String fname = Bottom_nav.user.getFname();
                        int currentBp  = Integer.parseInt(usrBP);
                        int newBP = currentBp + Integer.parseInt(bpToadd);

                        String finalBpValue = Integer.toString(newBP);

                        User updateBp = new User(fname, finalBpValue);

                        Useri useri = Url.getInstance().create(Useri.class);
                        Call<User> updateCall = useri.updateProfile(Url.token, updateBp);

                        updateCall.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {

                                if (!response.isSuccessful()) {
                                    Toast.makeText(getContext(), "Error !!", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                Toast.makeText(getContext(), "Purchase Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getContext(), com.rupesh.baji.activities.Bottom_nav.class));
                                return;
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Toast.makeText(getContext(), "Error!! " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    } catch (NumberFormatException ex) {

                    }
                }
            }
        });

        return builder.create();
    }

    public boolean CheckEmpty(){
        if(et_cardno.getText().toString().trim().isEmpty()) {
            et_cardno.setError("Empty field card number!!");
            return false;
        }
        return true;
    }
}
