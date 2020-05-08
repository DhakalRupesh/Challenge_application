package com.rupesh.baji.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChallengeDetail extends AppCompatActivity {

    ImageView imgChImage;
    TextView tvChallenger, tvChType, tvChGame, tvChBP, tvChDescription, tvChTime, tvChDate, tvChid;
    Challenge challenge;
    Button btnAcceptChallenge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_detail);

        imgChImage = findViewById(R.id.img_details_ch_image);

        tvChallenger = findViewById(R.id.tv_details_challenger);
        tvChType = findViewById(R.id.tv_details_chType);
        tvChGame = findViewById(R.id.tv_details_chGame);
        tvChBP = findViewById(R.id.tv_details_chBP);
        tvChDescription = findViewById(R.id.tv_details_chDesc);
        tvChTime = findViewById(R.id.tv_details_chTime);
        tvChDate = findViewById(R.id.tv_details_Date);
        tvChid = findViewById(R.id.tv_details_chID);

        btnAcceptChallenge = findViewById(R.id.btn_details_acceptChallenge);

        getDetails();

        btnAcceptChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String challenge_id = tvChid.getText().toString();
                String status = "true";
                User ChallengeAcceptedBy = new User(Bottom_nav.user.get_id());
                Challenge updateChallenge = new Challenge( ChallengeAcceptedBy, status);
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
        });
    }

    public void getDetails(){
        Bundle bundle = getIntent().getExtras();

        Bitmap bitmap = getIntent().getParcelableExtra("img");
        imgChImage.setImageBitmap(bitmap);

        if(bundle != null) {
//            String img = bundle.getString("chImg");
            String chId = bundle.getString("chID");

            String challenger = bundle.getString("challenger");
            String chEmail = bundle.getString("chEmail");
            String chType = bundle.getString("chType");
            String chGame = bundle.getString("chGame");
            String chBp = bundle.getString("chBP");
            String chDesc = bundle.getString("chDesc");
            String chTime = bundle.getString("chTime");
            String chDate = bundle.getString("chDate");


//            imgChImage.setImageResource(img);
            tvChid.setText(chId);
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
}
