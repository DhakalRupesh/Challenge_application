package com.rupesh.baji;

import android.widget.Toast;

import com.rupesh.baji.api.Challengei;
import com.rupesh.baji.model.Challenge;
import com.rupesh.baji.serverresponse.ImageResponse;
import com.rupesh.baji.url.Url;

import org.junit.Test;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChallengeTest {
    @Test
    public void AddChallenge(){
        Challenge myChallenge = new Challenge(
                "ChallengeType",
                "ChallengeGame",
                "ChallengePoint",
                "ChallengeDate",
                "ChallengeTime",
                "ChallengeDesc",
                "imageName",
                "Status",
                "confirmation");

        Challengei mychallengei = Url.getInstance().create(Challengei.class);
        Call<Void> callChallenge = mychallengei.addChallenge(myChallenge);
        callChallenge.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });

    }
}
