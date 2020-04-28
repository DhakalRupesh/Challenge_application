package com.rupesh.baji.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.rupesh.baji.R;
import com.rupesh.baji.activities.AddChallenge;
import com.rupesh.baji.adapters.ChallengeAdapter;
import com.rupesh.baji.api.Challengei;
import com.rupesh.baji.model.Challenge;
import com.rupesh.baji.url.Url;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends Fragment {

    Activity activity = getActivity();
    List<Challenge> challengeList;
    ChallengeAdapter challengeAdapter;
    private static final String TAG = "Home";
    public RecyclerView featuredChallenges;
    Button btnAddNewChallenge;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        featuredChallenges = view.findViewById(R.id.rv_challenge_card);
        btnAddNewChallenge = view.findViewById(R.id.btn_home_addChallenge);

        btnAddNewChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddChallenge = new Intent(getContext(), AddChallenge.class);
                startActivity(goToAddChallenge);
            }
        });

        getAllChallenges();

        return view;
    }

    private void getAllChallenges(){
        Challengei allchallengei = Url.getInstance().create(Challengei.class);
        Call<List<Challenge>> listCall = allchallengei.getAllChallenges(Url.token);

        listCall.enqueue(new Callback<List<Challenge>>() {
            @Override
            public void onResponse(Call<List<Challenge>> call, Response<List<Challenge>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                    return;
                }
                challengeList = response.body();
                challengeAdapter = new ChallengeAdapter(getContext(), challengeList);
                featuredChallenges.setAdapter(challengeAdapter);
                featuredChallenges.setLayoutManager(new GridLayoutManager(getContext(), 2));
            }

            @Override
            public void onFailure(Call<List<Challenge>> call, Throwable t) {
                Toast.makeText(activity, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailureHome: " + t.getLocalizedMessage());
            }
        });
    }
}
