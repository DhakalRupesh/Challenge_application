package com.rupesh.baji.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.rupesh.baji.R;
import com.rupesh.baji.adapters.CurrentChallengeAdapter;
import com.rupesh.baji.api.Challengei;
import com.rupesh.baji.model.Challenge;
import com.rupesh.baji.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentChallenges extends Fragment {

    List<Challenge> challengeList;
    CurrentChallengeAdapter currentChallengeAdapter;
    private static final String TAG = "AcceptedChallenge";
    public RecyclerView rvAcceptedChallenge;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_current_challenges, container, false);

        rvAcceptedChallenge = view.findViewById(R.id.rv_accepted_challenge);

        getAcceptedChallenges();

        return view;
    }

    private void getAcceptedChallenges() {
        Challengei acceptedChallenge = Url.getInstance().create(Challengei.class);
        Call<List<Challenge>> acceptedChallengeCall = acceptedChallenge.getAcceptedChallenges(Url.token);

        acceptedChallengeCall.enqueue(new Callback<List<Challenge>>() {
            @Override
            public void onResponse(Call<List<Challenge>> call, Response<List<Challenge>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                    return;
                }
                challengeList = response.body();
                currentChallengeAdapter = new CurrentChallengeAdapter(getContext(), challengeList);
                rvAcceptedChallenge.setAdapter(currentChallengeAdapter);
                rvAcceptedChallenge.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Call<List<Challenge>> call, Throwable t) {
                Toast.makeText(getContext(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailureHome: " + t.getLocalizedMessage());
            }
        });
    }
}