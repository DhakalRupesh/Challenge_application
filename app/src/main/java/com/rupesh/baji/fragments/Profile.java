package com.rupesh.baji.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rupesh.baji.R;
import com.rupesh.baji.activities.Login;
import com.rupesh.baji.activities.ProfileEdit;
import com.rupesh.baji.api.Useri;
import com.rupesh.baji.model.User;
import com.rupesh.baji.url.Url;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static androidx.constraintlayout.widget.Constraints.TAG;

public class Profile extends Fragment {

    ImageView imgLogout, imgProfile, imgEditProfile;
    TextView tvUsername, tvBP;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);

            imgLogout = view.findViewById(R.id.img_profile_logout);
            imgProfile = view.findViewById(R.id.img_profile_Image);
            imgEditProfile = view.findViewById(R.id.img_edit_profile);

            tvUsername = view.findViewById(R.id.tv_profile_username);
            tvBP = view.findViewById(R.id.tv_profile_game_points);

            imgProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent editProfile = new Intent(getContext(), ProfileEdit.class);
                    startActivity(editProfile);
                }
            });

            imgEditProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent editProfile = new Intent(getContext(), ProfileEdit.class);
                    startActivity(editProfile);
                }
            });

            imgLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Logout();
                }
            });

            LoadUserData(view);

        return view;
    }

    private void LoadUserData(final View view) {
        Useri useri = Url.getInstance().create(Useri.class);
        Call<User> userCall = useri.getme(Url.token);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: imageError" + response.code());
                    return;
                }

                User user = response.body();
                String imgPath = Url.imagePath + response.body().getProImg();

                ImageView profile = view.findViewById(R.id.img_profile_Image);
                try{
                    Picasso.get().load(imgPath).into(profile);

                }catch (Exception e){
                    Picasso.get().load(R.drawable.profile_picture).into(profile);
                }

                tvUsername.setText(user.getFname());
                tvBP.setText("BP - " + user.getAmt());

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "ofFailure" + t.getLocalizedMessage());
            }
        });
    }

    private void Logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setMessage("Are you sure?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Baji",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.remove("token");
                editor.commit();
                Url.token = "Bearer ";
                Intent redirect_To_login = new Intent(getActivity(), Login.class);
                startActivity(redirect_To_login);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
