package com.rupesh.baji.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.rupesh.baji.R;
import com.rupesh.baji.adapters.ChallengeAdapter;
import com.rupesh.baji.adapters.CurrentChallengeAdapter;
import com.rupesh.baji.api.Challengei;
import com.rupesh.baji.model.Challenge;
import com.rupesh.baji.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AcceptedChallenge extends AppCompatActivity {

    List<Challenge> challengeList;
    CurrentChallengeAdapter currentChallengeAdapter;
    private static final String TAG = "AcceptedChallenge";
    public RecyclerView rvAcceptedChallenge;
    Button btnAddNewChallenge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accepted_challenge);

        rvAcceptedChallenge = findViewById(R.id.rv_accepted_challenge);

        getAcceptedChallenges();

    }

    private void getAcceptedChallenges() {

        Challengei acceptedChallenge = Url.getInstance().create(Challengei.class);
        Call<List<Challenge>> acceptedChallengeCall = acceptedChallenge.getAcceptedChallenges(Url.token);

        acceptedChallengeCall.enqueue(new Callback<List<Challenge>>() {
            @Override
            public void onResponse(Call<List<Challenge>> call, Response<List<Challenge>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(AcceptedChallenge.this, "Error", Toast.LENGTH_SHORT).show();
                    return;
                }
                challengeList = response.body();
                currentChallengeAdapter = new CurrentChallengeAdapter(AcceptedChallenge.this, challengeList);
                rvAcceptedChallenge.setAdapter(currentChallengeAdapter);
                rvAcceptedChallenge.setLayoutManager(new LinearLayoutManager(AcceptedChallenge.this));
            }

            @Override
            public void onFailure(Call<List<Challenge>> call, Throwable t) {
                Toast.makeText(AcceptedChallenge.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailureHome: " + t.getLocalizedMessage());
            }
        });

//        final Challengei AcceptedChallenge = Url.getInstance().create(Challengei.class);
//        Call<List<Challenge>> AcceptedChallengelistCall = AcceptedChallenge.getAcceptedChallenges(Url.token);
//
//        AcceptedChallengelistCall.enqueue(new Callback<List<Challenge>>() {
//            @Override
//            public void onResponse(Call<List<Challenge>> call, Response<List<Challenge>> response) {
//                if(!response.isSuccessful()){
//                    Toast.makeText(AcceptedChallenge.this, "Error", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                challengeList = response.body();
//                currentChallengeAdapter = new CurrentChallengeAdapter(AcceptedChallenge.this, challengeList);
//                rvAcceptedChallenge.setAdapter(currentChallengeAdapter);
//                rvAcceptedChallenge.setLayoutManager(new LinearLayoutManager(AcceptedChallenge.this));
//            }
//
//            @Override
//            public void onFailure(Call<List<Challenge>> call, Throwable t) {
//                Toast.makeText(AcceptedChallenge.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                Log.e(TAG, "onFailureHome: " + t.getLocalizedMessage());
//            }
//        });

    }
}
