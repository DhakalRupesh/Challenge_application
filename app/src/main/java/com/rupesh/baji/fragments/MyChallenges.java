package com.rupesh.baji.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rupesh.baji.R;
import com.rupesh.baji.adapters.ChallengeAdapter;
import com.rupesh.baji.adapters.MyChallengeAdapter;
import com.rupesh.baji.api.Challengei;
import com.rupesh.baji.model.Challenge;
import com.rupesh.baji.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyChallenges extends Fragment {


    Activity activity = getActivity();
    List<Challenge> challengeList;
    MyChallengeAdapter mychallengeAdapter;
    private static final String TAG = "Home";
    public RecyclerView myChallenges;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_challenges, container, false);

        myChallenges = view.findViewById(R.id.rv_my_challenge_card);
        loadMyChallenges();

        return view;
    }

    public void loadMyChallenges() {
        Challengei allchallengei = Url.getInstance().create(Challengei.class);
        Call<List<Challenge>> listCallMyChallenges = allchallengei.getMyChallenges(Url.token);

        listCallMyChallenges.enqueue(new Callback<List<Challenge>>() {
            @Override
            public void onResponse(Call<List<Challenge>> call, Response<List<Challenge>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                    return;
                }
                challengeList = response.body();
                mychallengeAdapter = new MyChallengeAdapter(getContext(), challengeList);
                myChallenges.setAdapter(mychallengeAdapter);
                myChallenges.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Call<List<Challenge>> call, Throwable t) {
                Toast.makeText(activity, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailureHome: " + t.getLocalizedMessage());
            }
        });

    }
}