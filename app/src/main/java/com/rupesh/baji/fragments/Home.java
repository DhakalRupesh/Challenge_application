package com.rupesh.baji.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rupesh.baji.R;
import com.rupesh.baji.adapters.ChallengeAdapter;
import com.rupesh.baji.model.Challenge;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.rv_challenge_card);

        List<Challenge> challengeList = new ArrayList<>();

        challengeList.add(new Challenge("gamer", "pubg", "200", R.drawable.fortnite));
        challengeList.add(new Challenge("gamer", "pubg", "200", R.drawable.fortnite));
        challengeList.add(new Challenge("gamer", "pubg", "200", R.drawable.fortnite));
        challengeList.add(new Challenge("gamer", "pubg", "200", R.drawable.fortnite));
        challengeList.add(new Challenge("gamer", "pubg", "200", R.drawable.fortnite));
        challengeList.add(new Challenge("gamer", "pubg", "200", R.drawable.fortnite));
        challengeList.add(new Challenge("gamer", "pubg", "200", R.drawable.fortnite));
        challengeList.add(new Challenge("gamer", "pubg", "200", R.drawable.fortnite));
        challengeList.add(new Challenge("gamer", "pubg", "200", R.drawable.fortnite));
        challengeList.add(new Challenge("gamer", "pubg", "200", R.drawable.fortnite));

        ChallengeAdapter challengeAdapter = new ChallengeAdapter(getContext(), challengeList);
        recyclerView.setAdapter(challengeAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        return view;
    }
}
