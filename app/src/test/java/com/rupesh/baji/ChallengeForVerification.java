package com.rupesh.baji;

import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.rupesh.baji.adapters.ResultVerifyAdapter;
import com.rupesh.baji.api.Challengei;
import com.rupesh.baji.model.Challenge;
import com.rupesh.baji.url.Url;

import org.junit.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChallengeForVerification {
    List<Challenge> resultList;
    @Test
    public void GetChVerification() {
        try{
            Challengei verifyChallenge = Url.getInstance().create(Challengei.class);
            Call<List<Challenge>> acceptedChallengeCall = verifyChallenge.getChallengeForVerification(Url.token);

            acceptedChallengeCall.enqueue(new Callback<List<Challenge>>() {
                @Override
                public void onResponse(Call<List<Challenge>> call, Response<List<Challenge>> response) {
                    if(!response.isSuccessful()){
                        return;
                    }
                    resultList = response.body();
                }

                @Override
                public void onFailure(Call<List<Challenge>> call, Throwable t) {

                }
            });
        } catch (IllegalStateException e) {

        }
    }
}
