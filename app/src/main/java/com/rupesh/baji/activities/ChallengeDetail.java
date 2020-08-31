package com.rupesh.baji.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rupesh.baji.R;
import com.rupesh.baji.api.Challengei;
import com.rupesh.baji.model.Challenge;
import com.rupesh.baji.model.User;
import com.rupesh.baji.url.Url;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChallengeDetail extends AppCompatActivity {

    ImageView imgChImage;
    TextView tvChallenger, tvChallengerID, tvBnav, tvChType, tvChGame, tvChBP, tvChDescription, tvChTime, tvChDate, tvChid, tvChallenge, tv_details_challengeStatus;
    Button btnAcceptChallenge;
    String userIDHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_detail);

        tvChallenge = findViewById(R.id.tv_details_challengerinfo);
        tvChallenger = findViewById(R.id.tv_details_challenger);
        tvChallengerID = findViewById(R.id.tv_details_challengerID);
        tvBnav = findViewById(R.id.tv_Bnav_id);
        tvChType = findViewById(R.id.tv_details_chType);
        tvChGame = findViewById(R.id.tv_details_chGame);
        tvChBP = findViewById(R.id.tv_details_chBP);
        tvChDescription = findViewById(R.id.tv_details_chDesc);
        tvChTime = findViewById(R.id.tv_details_chTime);
        tvChDate = findViewById(R.id.tv_details_Date);
        tvChid = findViewById(R.id.tv_details_chID);
        tv_details_challengeStatus = findViewById(R.id.tv_details_challengeStatus);

        userIDHolder = Bottom_nav.user.get_id();

        btnAcceptChallenge = findViewById(R.id.btn_details_acceptChallenge);

        getDetails();
        getImage();


        Bundle bundle1 = getIntent().getExtras();
        String challengerID = bundle1.getString("userID");

        Bundle bundle2 = getIntent().getExtras();
        String chStatus = bundle2.getString("chStatus");

        tv_details_challengeStatus.setText(chStatus);

        if(Bottom_nav.user.get_id().equals(challengerID) || chStatus.equals("true")){
            btnAcceptChallenge.setVisibility(View.INVISIBLE);
        }

        btnAcceptChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBp()) {
                    String challenge_id = tvChid.getText().toString();
                    String status = "true";
                    User ChallengeAcceptedBy = new User(Bottom_nav.user.get_id());
                    Challenge updateChallenge = new Challenge(ChallengeAcceptedBy, status);

                    Challengei challengei = Url.getInstance().create(Challengei.class);
                    Call<Void> challengeVoidCall = challengei.updateChallengeStatus(challenge_id, updateChallenge);

                    challengeVoidCall.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(ChallengeDetail.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                                return;
                            }

                            Toast.makeText(ChallengeDetail.this, "accepted", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(ChallengeDetail.this, "Error!! " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        tvChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentProfile = new Intent(ChallengeDetail.this, ChallengerProfile.class);

                intentProfile.putExtra("challengerID", challengerID);

                startActivity(intentProfile);
            }
        });

        tvChallenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentProfile = new Intent(ChallengeDetail.this, ChallengerProfile.class);

                intentProfile.putExtra("challengerID", challengerID);

                startActivity(intentProfile);
            }
        });

    }

    public void getImage(){
        Bundle bundleImage = getIntent().getExtras();
        String challengeID = bundleImage.getString("chID");

        Challengei getSingleChallenge = Url.getInstance().create(Challengei.class);
        Call<Challenge> listCallSingle = getSingleChallenge.getOneChallenge(challengeID);

        listCallSingle.enqueue(new Callback<Challenge>() {
            @Override
            public void onResponse(Call<Challenge> call, Response<Challenge> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    return;
                }
                String imgPath = Url.imagePath + response.body().getChImage();

                ImageView challengeImage = findViewById(R.id.img_details_ch_image);
                try{
                    Picasso.get().load(imgPath).into(challengeImage);

                }catch (Exception e){
                    Picasso.get().load(R.drawable.fortnite).into(challengeImage);
                }
            }

            @Override
            public void onFailure(Call<Challenge> call, Throwable t) {

            }
        });
    }

    public void getDetails(){
        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            String chId = bundle.getString("chID");
            String challenger = bundle.getString("challenger");
//            String challengerID = bundle.getString("userID");
            String chEmail = bundle.getString("chEmail");
            String chType = bundle.getString("chType");
            String chGame = bundle.getString("chGame");
            String chBp = bundle.getString("chBP");
            String chDesc = bundle.getString("chDesc");
            String chTime = bundle.getString("chTime");
            String chDate = bundle.getString("chDate");
            String chStatus = bundle.getString("chStatus");


            tvChid.setText(chId);
//            tvChallengerID.setText(challengerID + "ChallengerID");
            tvChallenger.setText(challenger);
            tvChType.setText(chType);
            tvChBP.setText(chBp);
            tvChDescription.setText(chDesc);
            tvChTime.setText(chTime);
            tvChDate.setText(chDate);
            tvChGame.setText(chGame);

        } else {
            Toast.makeText(this, "Cant get this item", Toast.LENGTH_LONG).show();
        }
    }

    private boolean checkBp(){
        int number  = Integer.parseInt(Bottom_nav.user.getAmt());
        try {
            if(number < Integer.parseInt(tvChBP.getText().toString())){
                tvChBP.setError("You don't have " + tvChBP.getText().toString() + " points");
                Toast.makeText(this, "You don't have enough BP points to accept this challenge", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (NumberFormatException ex){

        }
        return true;
    }
}
