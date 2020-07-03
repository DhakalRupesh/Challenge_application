package com.rupesh.baji.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rupesh.baji.R;
import com.rupesh.baji.api.Useri;
import com.rupesh.baji.model.User;
import com.rupesh.baji.url.Url;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ChallengerProfile extends AppCompatActivity {

    private TextView tvUsername, tvBP, tvFullname, tvEmail, tvPhone,
            tvChPlayed, tvChWon, tvChLoss;
    private static final int REQUEST_CALL = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenger_profile);

        tvUsername = findViewById(R.id.tv_myprofile_username);
        tvBP = findViewById(R.id.tv_myprofile_game_points);
        tvFullname = findViewById(R.id.tv_myprofile_fullname);
        tvEmail = findViewById(R.id.tv_myprofile_email);
        tvPhone =findViewById(R.id.tv_myprofile_phone);

        tvChPlayed = findViewById(R.id.myprofile_chpalyed_info);
        tvChWon = findViewById(R.id.myprofile_chWon_info);
        tvChLoss = findViewById(R.id.myprofile_chLoss_info);

        LoadChallengerUser();

        tvPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = tvPhone.getText().toString();
                if(number.trim().length() > 0) {
                    if(ContextCompat.checkSelfPermission(ChallengerProfile.this, Manifest.permission.CALL_PHONE) !=
                            PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions( ChallengerProfile.this,
                                new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                    } else {
                        String dial = "tel:" + number;
                        ChallengerProfile.this.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                    }
                } else {
                    Toast.makeText( ChallengerProfile.this, "No phone number available", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                    emailIntent.putExtra(Intent.EXTRA_EMAIL  , tvEmail.getText());
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Bet24 update");
                    emailIntent.putExtra(Intent.EXTRA_TEXT   , "Enter your message");

                    emailIntent.setType("text/plain");
                    startActivity(emailIntent);
                } catch (Exception e){

                }
            }
        });
    }

    private void LoadChallengerUser() {
        Bundle bundleUserID = getIntent().getExtras();
        String challengerID = bundleUserID.getString("challengerID");

        Useri useri = Url.getInstance().create(Useri.class);
        Call<User> callUserProfile = useri.getOneUser(challengerID);

        callUserProfile.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ChallengerProfile.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: error" + response.code());
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

                tvUsername.setText(user.getFname());
                tvBP.setText("BP - " + user.getAmt());
                tvFullname.setText(user.getFname());
                tvEmail.setText(user.getEmail());
                tvPhone.setText(user.getPhone());

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}