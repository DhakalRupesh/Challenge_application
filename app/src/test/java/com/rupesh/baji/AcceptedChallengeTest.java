package com.rupesh.baji;

import com.rupesh.baji.api.Challengei;
import com.rupesh.baji.model.Challenge;
import com.rupesh.baji.url.Url;

import org.junit.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AcceptedChallengeTest {
    List<Challenge> challengeList;

    @Test
    public void getSingleChallenge() {
        Challengei acceptedChallenge = Url.getInstance().create(Challengei.class);
        Call<List<Challenge>> acceptedChallengeCall = acceptedChallenge.getAcceptedChallenges(Url.token);

        acceptedChallengeCall.enqueue(new Callback<List<Challenge>>() {
            @Override
            public void onResponse(Call<List<Challenge>> call, Response<List<Challenge>> response) {
                if(!response.isSuccessful()){
                    return;
                }
                challengeList = response.body();
            }

            @Override
            public void onFailure(Call<List<Challenge>> call, Throwable t) {
            }
        });
    }
}
