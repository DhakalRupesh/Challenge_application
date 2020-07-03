package com.rupesh.baji.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rupesh.baji.R;
import com.rupesh.baji.api.Useri;
import com.rupesh.baji.fragments.Profile;
import com.rupesh.baji.model.User;
import com.rupesh.baji.url.Url;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MyProfile extends AppCompatActivity {

    private TextView tvUsername, tvBP, tvFullname, tvEmail, tvPhone,
            tvChPlayed, tvChWon, tvChLoss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        tvUsername = findViewById(R.id.tv_myprofile_username);
        tvBP = findViewById(R.id.tv_myprofile_game_points);
        tvFullname = findViewById(R.id.tv_myprofile_fullname);
        tvEmail = findViewById(R.id.tv_myprofile_email);
        tvPhone =findViewById(R.id.tv_myprofile_phone);

        tvChPlayed = findViewById(R.id.myprofile_chpalyed_info);
        tvChWon = findViewById(R.id.myprofile_chWon_info);
        tvChLoss = findViewById(R.id.myprofile_chLoss_info);

        LoadUser();


    }

    private void LoadUser() {
        Useri useri = Url.getInstance().create(Useri.class);
        Call<User> userCall = useri.getme(Url.token);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MyProfile.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: imageError" + response.code());
                    return;
                }

                User user = response.body();
                String imgPath = Url.imagePath + response.body().getProImg();

                ImageView profile = findViewById(R.id.img_myprofile_Image);
                try{
                    Picasso.get().load(imgPath) .into(profile);

                }catch (Exception e){
                    Picasso.get().load(R.drawable.profile_picture).into(profile);
                }

                tvUsername.setText(user.getUname());
                tvBP.setText("BP - " + user.getAmt());
                tvFullname.setText(user.getFname());
                tvEmail.setText(user.getEmail());
                tvPhone.setText(user.getPhone());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "ofFailure" + t.getLocalizedMessage());
            }
        });
    }
}